package tech.getarrays.employeemanager.exception;

public class userNotFoundException extends RuntimeException{
    public userNotFoundException(String message) {
        //it will call the constructor on the class and pass the message
        super(message);
    }
}
