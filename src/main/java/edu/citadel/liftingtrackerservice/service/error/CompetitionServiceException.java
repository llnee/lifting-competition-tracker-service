package edu.citadel.liftingtrackerservice.service.error;

public class CompetitionServiceException extends Exception{

    public CompetitionServiceException() {
        super();
    }

    public CompetitionServiceException(String message) {
        super(message);
    }

    public CompetitionServiceException(Throwable clause) {
        super(clause);
    }

    public CompetitionServiceException(String message, Throwable clause) {
        super(message, clause);
    }

}
