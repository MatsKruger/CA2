package api;

import entity.Phone;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author ChristopherBorum
 */
@Path("phone")
public class PhoneResource extends AbstractFacade<Phone>{
    
    @Context
    private UriInfo context;
    
    @PersistenceContext(unitName = "PU")
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
    private EntityManager em = emf.createEntityManager();
    
    
    public PhoneResource() {
        super(Phone.class);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPhone() {
        return super.findAll();
    }

    /**
     * PUT method for updating or creating an instance of PersonResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
