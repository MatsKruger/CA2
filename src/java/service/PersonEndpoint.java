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
import entity.Hobby;
import entity.Person;
import entity.Phone;
import facade.HobbyFacade;
import facade.PersonFacade;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author matskruger
 */
@Path("person")
public class PersonEndpoint {

    @Context
    private UriInfo context;
    private EntityManagerFactory emf;
    private PersonFacade pf;
    private HobbyFacade hf;
    private Gson gson;
    
    
    public PersonEndpoint() {
        emf = Persistence.createEntityManagerFactory("PUTest");
        pf = new PersonFacade(emf);
        hf = new HobbyFacade(emf);
        gson = new GsonBuilder().setPrettyPrinting().create();
    }
    
    
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() {
        JsonArray json = new JsonArray();
        for (Person person : pf.getPersons()) {
            json.add(this.getPersonJsonObj(person));
        }
        
        return gson.toJson(json); 
    }
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String get(@PathParam("id") int id) {
        return gson.toJson(this.getPersonJsonObj(pf.getPerson(id), true));
    }
    @GET
    @Path("/hobby/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllByHobby(@PathParam("id") int id) {
        JsonArray json = new JsonArray();
        for (Person person : hf.getHobby(id).getPersons()) {
            json.add(this.getPersonJsonObj(person));
        }
        
        return gson.toJson(json);  
    }
    
    private JsonObject getPersonJsonObj(Person p) {
        JsonObject obj = new JsonObject();
        obj.addProperty("firstName", p.getFirstName());
        obj.addProperty("lastName", p.getLastName());
        obj.addProperty("email", p.getEmail());
        obj.addProperty("address", gson.toJson(p.getAddress(), Address.class));
        obj.addProperty("phones", gson.toJson(p.getPhones()));

        return obj;
    }
    
    private JsonObject getPersonJsonObj(Person p, boolean includeHobbies) {
        JsonObject obj = getPersonJsonObj(p);
        JsonArray hobbies = new JsonArray();
        for (Hobby hobby : p.getHobbies()) {
            JsonObject hobbyObj = new JsonObject();
            hobbyObj.addProperty("name", hobby.getName());
            hobbyObj.addProperty("description", hobby.getDescription());
        }
        obj.add("hobbies", hobbies);
        
        return obj;
    }
    

    
}
