/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tmar.sotomac;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tmar.sotomac.Entity.Cheque;
import tmar.sotomac.Entity.Personne;
import tmar.sotomac.Repository.ChequeRepository;
import tmar.sotomac.Repository.PersonneRepository;
import tmar.sotomac.service.ChequeService;

/**
 *
 * @author Bilel-PC
 */
@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(PersonneRepository repository, ChequeRepository chequeRepo, ChequeService service) {
        return (args) -> {
            // save a couple of personnes
            Date d = new Date(2016, 10, 02);
            Date d2 = new Date();
            d2.setYear(115);
            d2.setMonth(02);
            d2.setDate(13);
            System.out.println(d2);
            
            
            repository.save(new Personne("Mohamed Saleh"));
            repository.save(new Personne("Salim"));
            repository.save(new Personne("Emizioune"));
            repository.save(new Personne("Eva Nov"));
            repository.save(new Personne("Salim"));
            chequeRepo.save(new Cheque("1253",d2,200,repository.findById("1")));
            chequeRepo.save(new Cheque("1254",d2,200,repository.findById("2")));

           
        };
    }

}
