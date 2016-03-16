/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import entity.Address;
import entity.Company;
import facade.CompanyFacade;
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
    public String get(@PathParam("id") int id) {
        return gson.toJson(cf.getCompany(id));
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() {
        JsonArray json = new JsonArray();
        for (Company company : cf.getCompanys()) {
            JsonObject obj = new JsonObject();
            obj.addProperty("email", company.getEmail());
            obj.addProperty("address", gson.toJson(company.getAddress(), Address.class));
            obj.addProperty("phones", gson.toJson(company.getPhones())); 
            json.add(obj);
        }
        
        return gson.toJson(json);
        
        
    }
}
