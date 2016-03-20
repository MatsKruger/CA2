package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import deploy.DeploymentConfiguration;
import entity.Address;
import entity.Company;
import exception.CompanyNotFoundException;
import facade.CompanyFacade;
import facade.JSONConverter;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
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
        emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);
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
    public String getAll(@QueryParam("cvr") String cvr) {
        List<Company> companies;
        if (cvr != null) {
            companies = cf.getCompanyByCvr(cvr);
        } else {
            companies = cf.getCompanies();
        }
        return JSONConverter.getJSONFromCompanies(companies);    
    }
    
    @GET //Companies with more than x number of employees
    @Path("/moreemp/{numemp}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMore(@PathParam("numemp") int numemp) throws CompanyNotFoundException {
        return JSONConverter.getJSONFromCompanies(cf.getCompaniesByMoreThanNumEmployees(numemp));
    }
    
    @GET //Companies with less than x number of employees
    @Path("/lessemp/{numemp}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getLess(@PathParam("numemp") int numemp) throws CompanyNotFoundException {
        return JSONConverter.getJSONFromCompanies(cf.getCompaniesByLessThanNumEmployees(numemp));
    }
    
    @GET //Companies with less than x number of employees
    @Path("n/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getByName(@PathParam("name") String name) throws CompanyNotFoundException {
        return JSONConverter.getJSONFromCompanies(cf.getCompaniesByName(name));
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String createCompany(String json) {
        Company c = JSONConverter.getCompanyFromJson(json);
        cf.addCompany(c);
        return JSONConverter.getJSONFromCompany(c);
    }
    
}
