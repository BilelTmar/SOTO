/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tmar.sotomac.service;

import com.google.common.collect.Lists;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import tmar.sotomac.Entity.Cheque;
import tmar.sotomac.Repository.ChequeRepository;
import tmar.sotomac.util.QueryService;

/**
 *
 * @author Bilel-PC
 */
@Service
public class ChequeServiceImpl implements ChequeService {

    private static final Logger LOG = LoggerFactory.getLogger(ChequeService.class);

    ChequeRepository repo;
    QueryService<Cheque> search;

    public ChequeServiceImpl() {
    }

    @Autowired
    public ChequeServiceImpl(ChequeRepository repo, EntityManager em) {
        this.repo = repo;
    }

    @Override
    public Cheque create() {
        Cheque cheque = new Cheque();
        return cheque;
    }

    @Override
    public Cheque save(Cheque cheque) {
        LOG.info(cheque.toString());
        return this.repo.save(cheque);
    }

    @Override
    public Cheque read(String id) {
        Cheque result = this.repo.findById(id);
        if (result == null) {
            // TODO
            LOG.warn(String.format("found no cheque with oid '%s'", id));
            return null;
        } else {
            return result;
        }
    }

    @Override
    public Cheque update(Cheque cheque) {
        return this.repo.save(cheque);
    }

    @Override
    public void delete(String id) {
        Cheque item = this.read(id);
        this.repo.delete(item);
    }

    @Override
    public List<Cheque> query() {
        Iterable<Cheque> all = this.repo.findAll();
        return Lists.newArrayList(all);
    }

    @Override
    public List<Cheque> query(String query) {
        return this.search.query(query);
    }

    @Override
    public Cheque copy(String id) {
        Cheque source = this.read(id);
        Cheque result = null;
        Cheque clone = new Cheque();
        // start mapping
        clone.setDestination(source.getDestination());
        clone.setMontant(source.getMontant());
        clone.setNumero(source.getNumero());
        clone.setSource(source.getSource());
        clone.setTitulaire(source.getTitulaire());
        // end mapping
        LOG.info("clone --> " + clone.toString());
        result = this.repo.save(clone);
        return result;
    }

    @Override
    public List<Cheque> findByTitulaireNom(String nom) {
        List<Cheque> result = this.repo.findByTitulaireNom(nom);
        if (result == null) {
            // TODO
            LOG.warn(String.format("found no cheque to the person with id '%s'", nom));
            return null;
        } else {
            return result;
        }
    }

    @Override
    public List<Cheque> findBySource(String id) {
        List<Cheque> result = this.repo.findBySourceId(id);
        if (result == null) {
            // TODO
            LOG.warn(String.format("found no cheque of the ti with id '%s'", id));
            return null;
        } else {
            return result;
        }
    }

    @Override
    public List<Cheque> findByDestination(String id) {
        List<Cheque> result = this.repo.findByDestinationId(id);
        if (result == null) {
            // TODO
            LOG.warn(String.format("found no cheque to the person with id '%s'", id));
            return null;
        } else {
            return result;
        }
    }

    @Override
    public List<Cheque> findByDate(Date date) {
        List<Cheque> result = this.repo.findByDate(date);
        if (result == null) {
            // TODO
            LOG.warn(String.format("found no cheque to the person with id '%s'", date));
            return null;
        } else {
            return result;
        }
    }

    @Override
    public List<Cheque> findByDateBetween(Date from, Date to) {
        List<Cheque> result = this.repo.findByDateBetween(from, to);
        if (result == null) {
            // TODO
            LOG.warn(String.format("found no cheque to the person with id ´"));
            return null;
        } else {
            return result;
        }
    }

    @Override
    public Cheque findByNumero(String id) {
        Cheque result = this.repo.findByNumero(id);
        if (result == null) {
            // TODO
            LOG.warn(String.format("found no cheque with oid '%s'", id));
            return null;
        } else {
            return result;
        }
    }

    @Override
    public List<Cheque> findByTitulaireNomAndDateBetween(String nom, Date from, Date to) {
        List<Cheque> result = this.repo.findByTitulaireNomAndDateBetween(nom, from, to);
        if (result == null) {
            // TODO
            LOG.warn(String.format("found no cheque to the person with id ´"));
            return null;
        } else {
            return result;
        }
    }

}
