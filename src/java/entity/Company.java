package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "company.findAll", query = "SELECT c from Company c"),
    @NamedQuery(name = "company.findById", query = "SELECT c from Company c WHERE c.id = :id"),
//    @NamedQuery(name = "company.findByPhone", query = "SELECT c from Company c WHERE c.phone.number = :number"),
    @NamedQuery(name = "company.findByCvr", query = "SELECT c from Company c WHERE c.cvr = :cvr"),
    @NamedQuery(name = "company.findByMoreThanNumEmployees", query = "SELECT c from Company c WHERE c.numEmployees > :numEmployees"),
    @NamedQuery(name = "company.findByLessThanNumEmployees", query = "SELECT c from Company c WHERE c.numEmployees < :numEmployees"),
})

public class Company extends InfoEntity implements Serializable {

    private String name;
    private String description;
    private String cvr;
    private int numEmployees;
    private double marketValue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCvr() {
        return cvr;
    }

    public void setCvr(String cvr) {
        this.cvr = cvr;
    }

    public int getNumEmployees() {
        return numEmployees;
    }

    public void setNumEmployees(int numEmployees) {
        this.numEmployees = numEmployees;
    }

    public double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(double marketValue) {
        this.marketValue = marketValue;
    }
    
    
    
}
