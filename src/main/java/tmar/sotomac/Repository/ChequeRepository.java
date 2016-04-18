/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tmar.sotomac.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import tmar.sotomac.Entity.Cheque;

/**
 *
 * @author Bilel-PC
 */
public interface ChequeRepository extends CrudRepository<Cheque, Long> {

    List<Cheque> findByTitulaireNom(String nom);

    List<Cheque> findBySourceId(String id);

    List<Cheque> findByDestinationId(String id);

    Cheque findById(String id);

    List<Cheque> findByDate(Date d);

    Cheque findByNumero(String num);

    List<Cheque> findByDateBetween(Date from, Date to);

    List<Cheque> findByTitulaireAndDate(String titulaire, Date date);

    List<Cheque> findByTitulaireNomAndDate(String titulaire, Date date);

    List<Cheque> findByTitulaireNomAndDateBetween(String titulaire, Date from, Date to);
}
