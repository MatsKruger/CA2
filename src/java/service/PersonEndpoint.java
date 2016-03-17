/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import entity.Address;
import entity.Hobby;
import entity.Person;
import entity.Phone;
import exception.HobbyNotFoundException;
import exception.PersonNotFoundException;
import facade.HobbyFacade;
import facade.JSONConverter;
import facade.PersonFacade;
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
    public String getAll(@QueryParam("hobby") Integer hobbyId) throws HobbyNotFoundException {
        List<Person> persons;
        if (hobbyId != null) {
            persons = hf.getHobby(hobbyId).getPersons();
        } else {
            persons = pf.getPersons();
        }
        return JSONConverter.getJSONFromPerson(persons); 
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String get(@PathParam("id") int id) throws PersonNotFoundException {
        return JSONConverter.getJSONFromPerson(pf.getPerson(id));
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String createPerson(String json) {
        Person p = pf.addPerson(gson.fromJson(json, Person.class));
        return gson.toJson(JSONConverter.getJsonObjectFromPerson(p, true)); //return same object or exception if failed?
    }

}
