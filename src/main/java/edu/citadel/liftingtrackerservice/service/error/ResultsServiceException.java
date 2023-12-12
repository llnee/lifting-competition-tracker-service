package edu.citadel.liftingtrackerservice.service.error;

public class ResultsServiceException extends Exception{

    public ResultsServiceException() {
        super();
    }

    public ResultsServiceException(String message) {
        super(message);
    }

    public ResultsServiceException(Throwable clause) {
        super(clause);
    }

    public ResultsServiceException(String message, Throwable clause) {
        super(message, clause);
    }

}
