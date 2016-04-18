/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tmar.sotomac.service;

import java.util.List;
import tmar.sotomac.Entity.Personne;

/**
 *
 * @author Bilel-PC
 */
public interface PersonneService {
    
    public List<Personne> query();
    
    public List<Personne> query(String query);
    
    public Personne create();
    
    public Personne save(Personne personne);
    
    public Personne read(String id);
    
    public Personne update(Personne personne);
    
    public void delete(String id);
    
    public Personne copy(String id);
        public Personne findByNom(String nom);

    
}
