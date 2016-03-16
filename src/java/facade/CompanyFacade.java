package facade;

import entity.Company;
import entity.Company;
import interfaces.ICompanyFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class CompanyFacade implements ICompanyFacade {
    
    EntityManagerFactory emf;
    public CompanyFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Company addCompany(Company c) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
        } finally { 
            em.close(); 
        }
        
        return c;
    }

    @Override
    public Company deleteCompany(int id) {
        
        EntityManager em = getEntityManager();
        Company c = em.find(Company.class, id);
        try {
            em.getTransaction().begin();
            em.remove(c);
            em.getTransaction().commit();
        } finally { 
            em.close(); 
        }
        
        return c;
    }

    @Override
    public Company getCompany(int id) {
        EntityManager em = getEntityManager();
        Company c = em.createNamedQuery("Company.findById", Company.class).setParameter("id", id).getSingleResult();
        return c;
    }

    @Override
    public List<Company> getCompanys() {
        EntityManager em = getEntityManager();
        return em.createNamedQuery("Company.findAll", Company.class).getResultList();
    }

    @Override
    public Company editCompany(Company c) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(c);
            em.getTransaction().commit();
        } finally { 
            em.close(); 
        }
        return c;
    }
    
    protected EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
}
