package edu.citadel.liftingtrackerservice.service;

import edu.citadel.liftingtrackerservice.api.model.CompetitionDomain;
import edu.citadel.liftingtrackerservice.api.model.RegisterDomain;
import edu.citadel.liftingtrackerservice.service.error.CompetitionServiceException;

public interface CompetitionService {

    CompetitionDomain persistCompetition(CompetitionDomain competitionDomain) throws CompetitionServiceException;
    CompetitionDomain getCompetition(int competitionId) throws CompetitionServiceException;
    CompetitionDomain updateCompetition(CompetitionDomain competitionDomain) throws CompetitionServiceException;
    void finalizeCompetition(int competitionId);

    RegisterDomain addMemberToCompetition(RegisterDomain registerDomain) throws CompetitionServiceException;
}
