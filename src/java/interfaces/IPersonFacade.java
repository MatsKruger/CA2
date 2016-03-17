package interfaces;

import entity.Person;
import exception.PersonNotFoundException;
import java.util.List;

public interface IPersonFacade {

    public Person addPerson(Person p);

    public Person deletePerson(int id);

    public Person getPerson(int id) throws PersonNotFoundException;

    public List<Person> getPersons();

    public Person editPerson(Person p);
    
    public List<Person> getPersonsByCity(String city);
    
    public List<Person> getPersonsByZip(String zipCode);
}
