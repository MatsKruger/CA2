package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import entity.Address;
import entity.Company;
import exception.CompanyNotFoundException;
import facade.CompanyFacade;
import facade.JSONConverter;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author matskruger
 */
@Path("company")
public class CompanyEndpoint {

    @Context
    private UriInfo context;
    private EntityManagerFactory emf;
    private CompanyFacade cf;
    private Gson gson;
    
    
    public CompanyEndpoint() {
        emf = Persistence.createEntityManagerFactory("PUTest");
        cf = new CompanyFacade(emf);
        gson = new GsonBuilder().setPrettyPrinting().create();
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String get(@PathParam("id") int id) throws CompanyNotFoundException {
        return JSONConverter.getJSONFromCompany(cf.getCompany(id));
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() {
        return JSONConverter.getJSONFromCompanies(cf.getCompanies());    
    }
}
