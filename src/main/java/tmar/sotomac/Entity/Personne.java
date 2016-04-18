package tmar.sotomac.Entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Bilel-PC
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String nom;
    

    public Personne() {
    }

    public Personne(String nom) {
        this.nom = nom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return String.format(
                "Personne[id=%s, Nom='%s']",
                id, nom);
    }
}
