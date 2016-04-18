/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tmar.sotomac.Repository;
import tmar.sotomac.Entity.Personne;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
/**
 *
 * @author Bilel-PC
 */
public interface PersonneRepository extends CrudRepository<Personne, Long> {

    List<Personne> findByNom(String nom);
    Personne findById(String id);
}
