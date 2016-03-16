/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entity.Person;
import facade.PersonFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author matskruger
 */
public class PersonFacadeTest {
    
    private final EntityManagerFactory emf;
    private final PersonFacade personFacade;
    
    public PersonFacadeTest() {
        emf = Persistence.createEntityManagerFactory("PUTest");
        personFacade = new PersonFacade(emf);
    }
    
    @Test
    public void getPerson() {
        Person person = personFacade.getPerson(1);
        assertEquals((long) person.getId(), 1);
    }
    
    @Test
    public void createPerson() {
        Person person = new Person();
        Person testPerson = personFacade.addPerson(person);
        assertEquals(testPerson, person);
    }
    
    @Test
    public void getPersons() {
        List<Person> persons = personFacade.getPersons();
        assertEquals((long) persons.get(0).getId(), 1);
    }
}
