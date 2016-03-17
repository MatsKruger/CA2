package facade;

import entity.Person;
import exception.PersonNotFoundException;
import interfaces.IPersonFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class PersonFacade implements IPersonFacade {

    EntityManagerFactory emf;

    public PersonFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Person addPerson(Person p) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return p;
    }
    
    //@Override
    public List<Person> addPersons(List<Person> persons) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            for (Person person : persons) {
                em.persist(person);
            }
            
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return persons;
    }

    @Override
    public Person deletePerson(int id) {

        EntityManager em = getEntityManager();
        Person p = em.find(Person.class, id);
        try {
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return p;
    }

    @Override
    public Person getPerson(int id) throws PersonNotFoundException {
        EntityManager em = getEntityManager();
        Person p = em.find(Person.class, id);
        if (p == null) {
            throw new PersonNotFoundException("No Person found with provided id");
        }
        return p;
    }

    @Override
    public List<Person> getPersons() {
        EntityManager em = getEntityManager();
        return em.createNamedQuery("Person.findAll", Person.class).getResultList();
    }

    @Override
    public Person editPerson(Person p) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return p;
    }

    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
