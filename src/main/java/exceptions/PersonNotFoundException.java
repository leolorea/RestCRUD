package exceptions;

public class PersonNotFoundException extends Exception {
	
	 public PersonNotFoundException(Long id) {
	        super(String.format("Person with ID %s not found!", id));
	    }

}
