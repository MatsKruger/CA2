package test;

import entity.Company;
import entity.Hobby;
import entity.Person;
import facade.CompanyFacade;
import facade.HobbyFacade;
import facade.PersonFacade;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Tester {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PUTest");
        PersonFacade personFacade = new PersonFacade(emf);
        CompanyFacade companyFacade = new CompanyFacade(emf);
        HobbyFacade hobbyFacade = new HobbyFacade(emf);
        
        personFacade.addPerson(new Person());
        personFacade.addPerson(new Person());
        personFacade.addPerson(new Person());
        personFacade.addPerson(new Person());
        
        companyFacade.addCompany(new Company());
        companyFacade.addCompany(new Company());
        companyFacade.addCompany(new Company());
        companyFacade.addCompany(new Company());
        
        hobbyFacade.addHobby(new Hobby());
        hobbyFacade.addHobby(new Hobby());
        hobbyFacade.addHobby(new Hobby());
        hobbyFacade.addHobby(new Hobby());
        
    }
}
