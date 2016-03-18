package facade;

import entity.Hobby;
import exception.HobbyNotFoundException;
import interfaces.IHobbyFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class HobbyFacade implements IHobbyFacade{

    EntityManagerFactory emf;

    public HobbyFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    
    @Override
    public Hobby addHobby(Hobby h) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(h);
            em.getTransaction().commit();
        } finally { 
            em.close(); 
        }
        
        return h;
    }
    
    //@Override
    public List<Hobby> addHobbies(List<Hobby> hobbies) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            for (Hobby person : hobbies) {
                em.persist(person);
            }
            
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return hobbies;
    }

    @Override
    public Hobby deleteHobby(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Hobby getHobby(int id) throws HobbyNotFoundException {
        EntityManager em = getEntityManager();
        Hobby hobby = em.find(Hobby.class, id);
        if (hobby == null) {
            throw new HobbyNotFoundException("No Hobby found with provided id");
        }
        return hobby;
    }
    
    //@Override
    public List<Hobby> getHobbiesByName(String name) throws HobbyNotFoundException {
        EntityManager em = getEntityManager();
        List<Hobby> hobbies = em.createNamedQuery("Hobby.findAllByName").setParameter("name", '%' + name + '%').getResultList();
        
        return hobbies;
    }

    @Override
    public List<Hobby> getHobbies() {
        EntityManager em = getEntityManager();
        return em.createNamedQuery("Hobby.findAll", Hobby.class).getResultList();
    }

    @Override
    public Hobby editHobby(Hobby p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    protected EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
}
