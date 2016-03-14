package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Person extends InfoEntity implements Serializable {
    private String firstName;
    private String lastName;
    
    
    @ManyToMany
    private List<Hobby> hobbies;
}
