package test;

import entity.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Tester {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        EntityManager em = emf.createEntityManager();
        List<Person> res = em.createNamedQuery("person.findByPhoneNumber", Person.class)
                .setParameter("number", "12345678")
                .getResultList();
        for (Person p : res) {
            System.out.println(p.getFirstName() + p.getLastName() + p.getId());
        }
//        PersonFacade personFacade = new PersonFacade(emf);
//        
//        StudentFacade studentFacade = new StudentFacade(emf);
//        
//        Student stud1 = new Student();
//        
//        studentFacade.add(stud1);
//        
//        Student foundStud1 = studentFacade.find(1);
//        
//        System.out.println("Student found id: " + foundStud1.getId());
        
        
        
    }
}
