package interfaces;

import entity.Person;
import exception.PersonNotFoundException;
import java.util.List;

public interface IPersonFacade {

    public Person addPerson(Person p);

    public Person deletePerson(int id) throws PersonNotFoundException;

    public Person getPerson(int id) throws PersonNotFoundException;

    public List<Person> getPersons();

    public Person editPerson(Person p);
}
