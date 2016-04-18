/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tmar.sotomac.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import tmar.sotomac.Entity.Personne;
import tmar.sotomac.service.PersonneService;

/**
 *
 * @author Bilel-PC
 */
@CrossOrigin(maxAge = 3600)
@Controller
@ExposesResourceFor(PersonneController.class)
@RequestMapping("/Personne")
public class PersonneController {

    private static final Logger LOG = LoggerFactory.getLogger(PersonneController.class);

    @Autowired
    EntityLinks entityLinks;
    @Autowired
    PersonneService service;


    /**
     * Alle Personne suchen.
     *
     * @return
     */
    @RequestMapping(value = "/query", method = {RequestMethod.GET})
    public ResponseEntity queryPersonne() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("query Personne");
        }
        List<Personne> resource = this.service.query();
        return ResponseEntity.ok(resource);
    }

    /**
     * Personne mit Parametern suchen.
     *
     * @param filter
     * @return
     */
    @RequestMapping(value = "/query", params = {"filter"})
    public ResponseEntity queryPersonne(@RequestParam(value = "filter") String filter) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("query Personne with filter > " + filter);
        }
        List<Personne> resource = this.service.query(filter);
        return ResponseEntity.ok(resource);
    }

    /**
     * Erzeugt ein neues Personne Objekt mit gefüllter OID. Das Objekt ist noch
     * in der DB gespeichert.
     *
     * @return
     */
    @RequestMapping(value = "/new", method = {RequestMethod.GET})
    public ResponseEntity newPersonne() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("new Personne");
        }
        Personne entity = this.service.create();
        return ResponseEntity.ok(entity);
    }

    /**
     * Macht eine Kopie eines Büergers. Diese Kopie wird bei Erstellung in der
     * DB gespeichert.
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/copy/{id}", method = {RequestMethod.GET})
    public ResponseEntity copyPersonne(@PathVariable String id) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("copy Personne");
        }
        Personne entity = this.service.copy(id);
        return ResponseEntity.ok(entity);
    }

    /**
     * Liest einen Personne zur OID.
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    public ResponseEntity readPersonne(@PathVariable("id") String id) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("read Personne");
        }
        Personne entity = this.service.read(id);
        return ResponseEntity.ok(entity);
    }

    /**
     * Speichert Änderungen an einem bereits vorhandenen Personne.
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/{id}", method = {RequestMethod.POST})
    public ResponseEntity updatePersonne(@PathVariable("id") String id, @RequestBody Personne request) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("update Personne");
        }
        Personne entity = service.read(request.getId());
        entity = request;
        this.service.update(entity);
        return ResponseEntity.ok(entity);
    }

    /**
     * Speichert einen neuen Personne.
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public ResponseEntity savePersonne(@RequestBody Personne request) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("save Personne");
        }

        this.service.save(request);
        return ResponseEntity.ok(request);
    }

    /**
     * Löscht einen Personne.
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = {RequestMethod.DELETE})
    public ResponseEntity deletePersonne(@PathVariable("id") String id) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("delete Personne");
        }
        this.service.delete(id);
        return ResponseEntity.ok().build();
    }


}
