package facade;

import entity.Company;
import exception.CompanyNotFoundException;
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
    public Company getCompany(int id) throws CompanyNotFoundException{
        EntityManager em = getEntityManager();
        Company c = em.find(Company.class, id);
        if (c == null) throw new CompanyNotFoundException();
        return c;
    }

    @Override
    public List<Company> getCompanies() {
        EntityManager em = getEntityManager();
        return em.createNamedQuery("Company.findAll", Company.class).getResultList();
    }
    
    @Override
    public List<Company> getCompanyByCvr(String cvr) {
        EntityManager em = getEntityManager();
        return em.createNamedQuery("Company.findByCvr", Company.class)
                .setParameter("cvr", cvr)
                .getResultList();
    }
    
    @Override
    public List<Company> getCompaniesByMoreThanNumEmployees(int num) {
        EntityManager em = getEntityManager();
        return em.createNamedQuery("Company.findByMoreThanNumEmployees", Company.class)
                .setParameter("numEmployees", num)
                .getResultList();
    }
    
    @Override
    public List<Company> getCompaniesByLessThanNumEmployees(int num) {
        EntityManager em = getEntityManager();
        return em.createNamedQuery("Company.findByLessThanNumEmployees", Company.class)
                .setParameter("numEmployees", num)
                .getResultList();
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
