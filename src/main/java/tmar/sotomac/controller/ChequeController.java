package tmar.sotomac.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import org.apache.commons.collections.map.MultiValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import tmar.sotomac.Entity.Cheque;
import tmar.sotomac.Entity.Personne;
import tmar.sotomac.service.ChequeService;
import tmar.sotomac.service.PersonneService;

/**
 *
 * @author Bilel-PC
 */
@CrossOrigin(maxAge = 3600)
@Controller
@ExposesResourceFor(ChequeController.class)
@RequestMapping("/Cheque")
public class ChequeController {

    private static final Logger LOG = LoggerFactory.getLogger(ChequeController.class);

    @Autowired
    EntityLinks entityLinks;
    @Autowired
    ChequeService service;
    @Autowired
    PersonneService personneService;

    @RequestMapping(value = "/query", method = {RequestMethod.GET})
    public ResponseEntity queryCheque() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("query Cheque");
        }
        List<Cheque> resource = this.service.query();
        return ResponseEntity.ok(resource);
    }

    @RequestMapping(value = "/query", params = {"filter"})
    public ResponseEntity queryCheque(@RequestParam(value = "filter") String filter) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("query Cheque with filter > " + filter);
        }
        List<Cheque> resource = this.service.query(filter);
        MultiValueMap headers = new MultiValueMap();
        headers.put("Access-Control-Allow-Origin", "*");
        ResponseEntity response = new ResponseEntity(headers, HttpStatus.ACCEPTED);
        return ResponseEntity.ok(resource);
    }

    @RequestMapping(value = "/new", method = {RequestMethod.GET})
    public ResponseEntity newCheque() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("new Cheque");
        }
        Cheque entity = this.service.create();
        return ResponseEntity.ok(entity);
    }

    @RequestMapping(value = "/copy/{id}", method = {RequestMethod.GET})
    public ResponseEntity copyCheque(@PathVariable String id) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("copy Cheque");
        }
        Cheque entity = this.service.copy(id);
        return ResponseEntity.ok(entity);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    public ResponseEntity readCheque(@PathVariable("id") String id) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("read Cheque");
        }
        Cheque entity = this.service.read(id);
        return ResponseEntity.ok(entity);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.POST})
    public ResponseEntity updateCheque(@PathVariable("id") String id, @RequestBody Cheque request) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("update Cheque");
        }
        Cheque entity = service.read(request.getId());
        entity = request;
        this.service.update(entity);
        return ResponseEntity.ok(entity);
    }

    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public ResponseEntity saveCheque(@RequestBody Cheque request) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("save Cheque");
        }

        this.service.save(request);
        return ResponseEntity.ok(request);
    }

    @RequestMapping(value = "/{numeroCh}", method = {RequestMethod.DELETE})
    public ResponseEntity deleteCheque(@PathVariable("numeroCh") String numeroCh) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("delete Cheque");
        }        
        Cheque cheque = service.findByNumero(numeroCh);
        this.service.delete(cheque.getId());
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/titulaire/{nom}", method = {RequestMethod.GET})
    public ResponseEntity readTitulaireCheque(@PathVariable("nom") String nom) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("read Personne Cheques");
        }
        List<Cheque> cheques = this.service.findByTitulaireNom(nom);

        return ResponseEntity.ok(cheques);
    }

    @RequestMapping(value = "{numeroCh}/titulaire/{nomTitu}", method = {RequestMethod.GET})
    public ResponseEntity addChequeTitulaire(@PathVariable("numeroCh") String numeroCh, @PathVariable("nomTitu") String nomTitu) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Add titulaire to cheque  ");
        }

        Cheque cheque = service.findByNumero(numeroCh);
        Personne entity = personneService.findByNom(nomTitu);
        cheque.setTitulaire(entity);
        service.update(cheque);

        return ResponseEntity.ok(entity);
    }

    @RequestMapping(value = "create/titulaire/{id}", method = {RequestMethod.POST})
    public ResponseEntity createChequeTitulaire(@PathVariable("id") String id, @RequestBody Personne request) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Create Cheque with Personne");
        }
        this.personneService.save(request);
        Cheque entity = service.read(id);
        entity.setTitulaire(request);
        service.update(entity);

        return ResponseEntity.ok(entity);
    }

    @RequestMapping(value = "/source/{id}", method = {RequestMethod.GET})
    public ResponseEntity readSourceCheque(@PathVariable("id") String id) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("read Personne Cheques");
        }
        List<Cheque> cheques = this.service.findBySource(id);

        return ResponseEntity.ok(cheques);
    }

    @RequestMapping(value = "{cId}/source/{pId}", method = {RequestMethod.GET})
    public ResponseEntity addChequeSource(@PathVariable("cId") String cId, @PathVariable("pId") String pId) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Add source to cheque  ");
        }

        Cheque cheque = service.findByNumero(cId);
        Personne entity = personneService.findByNom(pId);
        cheque.setSource(entity);
        service.update(cheque);

        return ResponseEntity.ok(entity);
    }

    @RequestMapping(value = "/destination/{id}", method = {RequestMethod.GET})
    public ResponseEntity readDestinationCheque(@PathVariable("id") String id) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("read destination Cheques");
        }
        List<Cheque> cheques = this.service.findByDestination(id);

        return ResponseEntity.ok(cheques);
    }

    @RequestMapping(value = "{cId}/destination/{pId}", method = {RequestMethod.GET})
    public ResponseEntity addChequeDestination(@PathVariable("cId") String cId, @PathVariable("pId") String pId) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Add destination to cheque  ");
        }

        Cheque cheque = service.findByNumero(cId);
        Personne entity = personneService.findByNom(pId);
        cheque.setDestination(entity);
        service.update(cheque);

        return ResponseEntity.ok(entity);
    }

    @RequestMapping(value = "/numero/{num}", method = {RequestMethod.GET})
    public ResponseEntity readNumeroCheque(@PathVariable("num") String num) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("read Cheques numero");
        }
        Cheque cheques = this.service.findByNumero(num);

        return ResponseEntity.ok(cheques);
    }

    @RequestMapping(value = "/date/{date}", method = {RequestMethod.GET})
    public ResponseEntity readDateCheque(@PathVariable("date") Date dateS) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("read Cheques Date");
        }

        List<Cheque> cheques = this.service.findByDate(dateS);
        return ResponseEntity.ok(cheques);

    }

    @RequestMapping(value = "/debut/{from}/fin/{to}", method = {RequestMethod.GET})
    public ResponseEntity readBetweenDateCheque(@PathVariable("from") Date from, @PathVariable("to") Date to) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("read Cheques between Date");
        }
       
            List<Cheque> cheques = this.service.findByDateBetween(from, to);

            return ResponseEntity.ok(cheques);
        
    }
    @RequestMapping(value = "titulaire/{nom}/debut/{from}/fin/{to}", method = {RequestMethod.GET})
    public ResponseEntity readTitulaireBetweenDateCheque(@PathVariable("nom") String nom,@PathVariable("from") Date from, @PathVariable("to") Date to) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("read Cheques between Date");
        }
       
            List<Cheque> cheques = this.service.findByTitulaireNomAndDateBetween(nom,from, to);

            return ResponseEntity.ok(cheques);
        
    }
}
