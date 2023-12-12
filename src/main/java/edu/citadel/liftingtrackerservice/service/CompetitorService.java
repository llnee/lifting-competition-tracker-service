package edu.citadel.liftingtrackerservice.service;

import edu.citadel.liftingtrackerservice.api.model.CompetitorDomain;
import edu.citadel.liftingtrackerservice.service.error.CompetitionServiceException;

import java.util.List;

public interface CompetitorService {

    CompetitorDomain persistCompetitor(CompetitorDomain competitorDomain) throws CompetitionServiceException;
    CompetitorDomain getCompetitor(int memberId);
    List<CompetitorDomain> getCompetitorsByCompetitionId(int competitionId);
    CompetitorDomain updateCompetitor(CompetitorDomain competitorDomain) throws CompetitionServiceException;
}
