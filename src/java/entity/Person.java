package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
    @NamedQuery(name = "Person.findById", query = "SELECT p FROM Person p WHERE p.id = :id"),
    @NamedQuery(name = "Person.findByCity", query = "SELECT p FROM Person p WHERE p.address.city.city LIKE :city"), //needs "%" on each side when setting parameter
    @NamedQuery(name = "Person.findByZip", query = "SELECT p FROM Person p WHERE p.address.city.zipCode LIKE :zipCode")
})
@XmlRootElement
public class Person extends InfoEntity implements Serializable {
    private String firstName;
    private String lastName;

    
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Hobby> hobbies;

    public Person() {
    }

    public Person(String firstName, String lastName, List<Hobby> hobbies, String email, Address address, List<Phone> phones) {
        super(email, address, phones);
        this.firstName = firstName;
        this.lastName = lastName;
        this.hobbies = hobbies;
    }
    
    
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }
    
}
