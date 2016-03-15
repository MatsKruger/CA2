package facade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public abstract class AbstractFacade<T> {
    private EntityManagerFactory emf;
    private final Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass, EntityManagerFactory emf) {
        this.entityClass = entityClass;
        this.emf = emf;
    }

    public void add(T entity) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } finally { 
            em.close(); 
        }
    }

    public void edit(T entity) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        } finally { 
            em.close(); 
        }
    }

    public void delete(T entity) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
        } finally { 
            em.close(); 
        }
    }

    public T find(Object id) {
        EntityManager em = getEntityManager();
        return em.find(entityClass, id);
    }
    
    protected EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
}
