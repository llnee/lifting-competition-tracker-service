package edu.citadel.liftingtrackerservice.service.error;

public class EmailServiceException extends Exception{

    public EmailServiceException() {
        super();
    }

    public EmailServiceException(String message) {
        super(message);
    }

    public EmailServiceException(Throwable clause) {
        super(clause);
    }

    public EmailServiceException(String message, Throwable clause) {
        super(message, clause);
    }

}
