package api;

import com.google.gson.Gson;
import entity.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author ChristopherBorum
 */
public abstract class AbstractFacade<T> {
    private Class<T> entityClass;
    
    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    protected abstract EntityManager getEntityManager();
    
    public String findAll() {
        EntityManager em = getEntityManager();
        List res;
        try {
            em.getTransaction().begin();
            TypedQuery tq = em.createQuery("SELECT p FROM Person p", Person.class);
            res = tq.getResultList();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        
        return new Gson().toJson(res);
        
    }
    
}
