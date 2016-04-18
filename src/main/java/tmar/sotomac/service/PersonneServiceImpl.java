/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tmar.sotomac.service;

import com.google.common.collect.Lists;
import java.util.List;
import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tmar.sotomac.Entity.Personne;
import tmar.sotomac.Repository.PersonneRepository;
import tmar.sotomac.util.QueryService;

/**
 *
 * @author Bilel-PC
 */
@Service
public class PersonneServiceImpl implements PersonneService {

    private static final Logger LOG = LoggerFactory.getLogger(PersonneService.class);

    PersonneRepository repo;
    QueryService<Personne> search;

    public PersonneServiceImpl() {
    }

    @Autowired
    public PersonneServiceImpl(PersonneRepository repo, EntityManager em) {
        this.repo = repo;
    }

    @Override
    public Personne create() {
        Personne personne = new Personne();
        return personne;
    }

    @Override
    public Personne save(Personne personne) {
        LOG.info(personne.toString());
        return this.repo.save(personne);
    }

    @Override
    public Personne read(String id) {
        Personne result = this.repo.findById(id);
        if (result == null) {
            // TODO
            LOG.warn(String.format("found no personne with oid '%s'", id));
            return null;
        } else {
            return result;
        }
    }

    @Override
    public Personne update(Personne personne) {
        return this.repo.save(personne);
    }

    @Override
    public void delete(String id) {
        Personne item = this.read(id);
        this.repo.delete(item);
    }

    @Override
    public List<Personne> query() {
        Iterable<Personne> all = this.repo.findAll();
        return Lists.newArrayList(all);
    }

    @Override
    public List<Personne> query(String query) {
        return this.search.query(query);
    }

    @Override
    public Personne copy(String id) {
        Personne source = this.read(id);
        Personne result = null;
        Personne clone = new Personne();
        // start mapping
        clone.setNom(source.getNom());
        // end mapping
        LOG.info("clone --> " + clone.toString());
        result = this.repo.save(clone);
        return result;
    }

    @Override
    public Personne findByNom(String nom) {
        List<Personne> result = this.repo.findByNom(nom);
        if (result == null) {
            // TODO
            LOG.warn(String.format("found no personne with nom '%s'", nom));
            return null;
        } else {
            return result.get(0);
        }    }

}
