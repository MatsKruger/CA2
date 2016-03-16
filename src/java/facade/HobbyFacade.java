package facade;

import entity.Hobby;
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

    @Override
    public Hobby deleteHobby(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Hobby getHobby(int id) {
        EntityManager em = getEntityManager();
        return em.createNamedQuery("Hobby.findById", Hobby.class).setParameter("id", id).getSingleResult();
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
