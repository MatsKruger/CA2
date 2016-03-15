package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Person extends InfoEntity implements Serializable {
    private String firstName;
    private String lastName;
    
    
    @ManyToMany
    private List<Hobby> hobbies;
}
