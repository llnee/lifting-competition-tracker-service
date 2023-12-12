package edu.citadel.liftingtrackerservice.service;

import edu.citadel.liftingtrackerservice.api.model.EmailDomain;
import edu.citadel.liftingtrackerservice.service.error.CompetitionServiceException;

public interface EmailService {

    EmailDomain persistEmail(EmailDomain emailDomain) throws CompetitionServiceException;
    EmailDomain getEmail(int memberId) throws CompetitionServiceException;
    EmailDomain updateEmail(EmailDomain emailDomain) throws CompetitionServiceException;
}
