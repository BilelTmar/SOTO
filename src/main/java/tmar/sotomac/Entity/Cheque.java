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
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Cheque {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String numero;
    @Temporal(TemporalType.DATE)
    private Date date;
    private int montant;
    @ManyToOne
    private Personne titulaire;
    @ManyToOne
    private Personne source;
    @ManyToOne
    private Personne destination;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public Personne getTitulaire() {
        return titulaire;
    }

    public void setTitulaire(Personne titulaire) {
        this.titulaire = titulaire;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Personne getSource() {
        return source;
    }

    public void setSource(Personne source) {
        this.source = source;
    }

    public Personne getDestination() {
        return destination;
    }

    public void setDestination(Personne destination) {
        this.destination = destination;
    }

    public Cheque() {
    }

    public Cheque(String numero, Date date, int montant, Personne titulaire) {
        this.numero = numero;
        this.date = date;
        this.montant = montant;
        this.titulaire = titulaire;
    }



    @Override
    public String toString() {
        return String.format("Personne[id=%s, Tutilaire='%s', Montant='%s', Numero='%s', Source='%s', Destination='%s']",
                id, titulaire, montant, numero, source, destination);
    }
}
