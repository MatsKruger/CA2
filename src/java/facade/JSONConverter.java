package facade;

import com.google.common.reflect.TypeToken;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import entity.Address;
import entity.Company;
import entity.Hobby;
import entity.Person;
import java.util.List;

public class JSONConverter {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();

    // Person
    public static Person getPersonFromJson(String js) {
        return gson.fromJson(js, Person.class);
    }
    
    public static List<Person> getPersonsFromJson(String js) {
        return gson.fromJson(js, new TypeToken<List<Person>>() {}.getType());
    }
    
    public static String getJSONFromPerson(Person p) {
        //return gson.toJson(getJsonObjectFromPerson(p));
        return gsonIt(getJsonObjectFromPerson(p));
    }

    public static String getJSONFromPerson(Person p, boolean includeHobbies) {
        //return gson.toJson(getJsonObjectFromPerson(p, includeHobbies));
        return gsonIt(getJsonObjectFromPerson(p, includeHobbies));
    }

    public static String getJSONFromPerson(List<Person> persons) {
        //return gson.toJson(getJsonArrayFromPersonList(persons));
        return gsonIt(getJsonArrayFromPersonList(persons));
    }

    public static JsonArray getJsonArrayFromPersonList(List<Person> persons) {
        JsonArray json = getJsonArray();
        for (Person person : persons) {
            json.add(getJsonObjectFromPerson(person));
        }
        return json;
    }
    
    public static JsonObject getJsonObjectFromPerson(Person p) {
        JsonObject obj = getJsonObject();
        obj.addProperty("firstName", p.getFirstName());
        obj.addProperty("lastName", p.getLastName());
        obj.addProperty("email", p.getEmail());
        obj.add("address", gson.toJsonTree(p.getAddress(), Address.class));
        obj.add("phones", gson.toJsonTree(p.getPhones()));

        return obj;
    }

    public static JsonObject getJsonObjectFromPerson(Person p, boolean includeHobbies) {
        JsonObject obj = getJsonObjectFromPerson(p);
        JsonArray hobbies = getJsonArray();
        for (Hobby hobby : p.getHobbies()) {
            JsonObject hobbyObj = getJsonObject();
            hobbyObj.addProperty("name", hobby.getName());
            hobbyObj.addProperty("description", hobby.getDescription());
        }
        obj.add("hobbies", hobbies);

        return obj;
    }
    
    // Company 
    public static Company getCompanyFromJson(String js) {
        return gson.fromJson(js, Company.class);
    }
    
    public static String getJSONFromCompany(Company c) {
        //return gson.toJson(c);
        return gsonIt(c);
    }
    
    public static String getJSONFromCompanies(List<Company> company) {
        //return gson.toJson(getJsonArrayFromCompanyList(company));
        return gsonIt(getJsonArrayFromCompanyList(company));
    }
    
    public static JsonArray getJsonArrayFromCompanyList(List<Company> companies) {
        JsonArray json = getJsonArray();
        for (Company company : companies) {
            JsonObject obj = getJsonObject();
            obj.addProperty("email", company.getEmail());
            obj.add("address", gson.toJsonTree(company.getAddress(), Address.class));
            obj.add("phones", gson.toJsonTree(company.getPhones())); 
            json.add(obj);
        }
        return json;
    }
    
    private static String gsonIt(Object obj) {
        return gson.toJson(obj);
    }
    
    private static JsonArray getJsonArray() {
        return new JsonArray();
    }
    
    private static JsonObject getJsonObject() {
        return new JsonObject();
    }
    
    
}