package api;

import entity.Person;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
    
    /*
    Gets all persons in DB
    */
    @GET
    @Path("complete")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPerson() {
        return super.findAll();
    }
    
    /*
    Get person with id in the path
    */
    @GET
    @Path("complete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonId(@PathParam("id") String id) {
        int intId = Integer.parseInt(id);
        return super.find(intId);
    }
    
    /*
    Get contactinfo from all persons in db
    */
    @GET
    @Path("contactinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public String getContactinfo() {
        return super.findAllColumns("firstName,lastName,email,phone");
    }
    
    /*
    Get contact info from person with id in the path
    */
    @GET
    @Path("contactinfo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getContactinfoId(@PathParam("id") String id) {
        int intId = Integer.parseInt(id);
        return super.findAllColumnsId("firstName,lastName,email,phone", intId);
    }
    
    @GET
    @Path("phone/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonPhone(@PathParam("number") String phoneNumber) {
        return super.findAllCriteria("phone.number", phoneNumber);
    }
    
    /*
    Creates a new person in DB
        - should this return the person created?
    */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String personJson) {
        super.create(personJson);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
