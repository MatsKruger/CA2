package exception;

public class PersonNotFoundException extends Exception {

  public PersonNotFoundException(String string) {
    super(string);
  }
  public PersonNotFoundException() {
    super("Person with requested id not found");
  }
  
}
