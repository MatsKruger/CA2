/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import deploy.DeploymentConfiguration;
import entity.Hobby;
import exception.HobbyNotFoundException;
import facade.HobbyFacade;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author matskruger
 */
@Path("hobbies")
public class HobbyEndpoint {

    @Context
    private UriInfo context;
    private EntityManagerFactory emf;
    private HobbyFacade hf;
    private Gson gson;
    
    
    public HobbyEndpoint() {
        emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);
        hf = new HobbyFacade(emf);
        gson = new GsonBuilder().setPrettyPrinting().create();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String get(@QueryParam("q") String query) throws HobbyNotFoundException {
        List<Hobby> hobbies;
        
        if (query == null) {
            hobbies = hf.getHobbies();
        } else {
            hobbies = hf.getHobbiesByName(query);
        }
        return JSONConverter.getJSONFromHobbies(hobbies); 
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addHobby(String hobby) {
        hf.addHobby(JSONConverter.getHobbyFromJson(hobby));
    }
    
    @POST
    @Path("/bulk")
    @Consumes(MediaType.APPLICATION_JSON)
    public void post(String hobbies) {
        hf.addHobbies(JSONConverter.getHobbiesFromJson(hobbies));
    }

}
