package api;

import com.google.gson.Gson;
import entity.Person;
import java.util.Date;
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
@Path("person")
public class PersonResource extends AbstractFacade<Person>{
    
    @Context
    private UriInfo context;
    
    @PersistenceContext(unitName = "PU")
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
    private EntityManager em = emf.createEntityManager();
    
    public PersonResource() {
        super(Person.class);
    }

    /**
     * Retrieves representation of an instance of api.PersonResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
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
