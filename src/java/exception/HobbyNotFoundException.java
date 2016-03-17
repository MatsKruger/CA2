package exception;

public class HobbyNotFoundException extends Exception {

  public HobbyNotFoundException(String string) {
    super(string);
  }
  public HobbyNotFoundException() {
    super("Hobby with requested id not found");
  }
  
}
