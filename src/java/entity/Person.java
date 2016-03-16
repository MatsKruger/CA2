package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQueries({
    @NamedQuery(name = "person.findById", query = "SELECT p FROM Person p WHERE p.id = :id"),
    @NamedQuery(name = "person.findByCity", query = "SELECT p FROM Person p WHERE p.address.city.city LIKE :city"), //needs "%" on each side when setting parameter
    @NamedQuery(name = "person.findByZip", query = "SELECT p FROM Person p WHERE p.address.city.zipCode LIKE :zipCode"), 
    @NamedQuery(name = "person.findByZip", query = "SELECT p FROM Person p WHERE p.address.city.zipCode LIKE :zipCode")
//    @NamedQuery(name = "person.findByPhoneNumber", query = "SELECT p FROM Person p WHERE p.phone.number = :number"),
//    @NamedQuery(name = "person.findContactInfo", query = "SELECT p.firstName, p.lastName, p.email, p.phone FROM Person p"),
//    @NamedQuery(name = "person.findContactInfoById", query = "SELECT p.firstName, p.lastName, p.email, p.phone.id FROM Person p WHERE p.id = :id"),
})
@XmlRootElement
public class Person extends InfoEntity implements Serializable {
    private String firstName;
    private String lastName;
    
    @ManyToMany
    private List<Hobby> hobbies;

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
