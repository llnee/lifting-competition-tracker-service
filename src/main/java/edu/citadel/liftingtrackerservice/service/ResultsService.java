package edu.citadel.liftingtrackerservice.service;

import edu.citadel.liftingtrackerservice.api.model.ResultsDomain;

public interface ResultsService {

    int persistResults(ResultsDomain resultsDomain, int memberId, int competitionId);
    ResultsDomain getResults(int memberId, int competitionId);
    ResultsDomain updateResults(ResultsDomain resultsDomain, int memberId, int competitionId);


}
