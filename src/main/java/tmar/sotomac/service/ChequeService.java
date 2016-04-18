/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tmar.sotomac.service;

import java.util.Date;
import java.util.List;
import tmar.sotomac.Entity.Cheque;

/**
 *
 * @author Bilel-PC
 */
public interface ChequeService {

    public List<Cheque> query();

    public List<Cheque> query(String query);

    public Cheque create();

    public Cheque save(Cheque cheque);

    public Cheque read(String id);

    public Cheque update(Cheque cheque);

    public void delete(String id);

    public Cheque copy(String id);

    public List<Cheque> findByTitulaireNom(String id);

    public Cheque findByNumero(String id);

    public List<Cheque> findBySource(String id);

    public List<Cheque> findByDestination(String id);

    public List<Cheque> findByDate(Date date);

    public List<Cheque> findByDateBetween(Date from, Date to);
    public List<Cheque> findByTitulaireNomAndDateBetween(String nom,Date from, Date to);

}
