package edu.citadel.liftingtrackerservice.service.impl;

import edu.citadel.liftingtrackerservice.api.model.ResultsDomain;
import edu.citadel.liftingtrackerservice.dal.CompJoinRepository;
import edu.citadel.liftingtrackerservice.dal.ResultsRepository;
import edu.citadel.liftingtrackerservice.dal.entity.CompJoin;
import edu.citadel.liftingtrackerservice.dal.entity.Results;
import edu.citadel.liftingtrackerservice.service.ResultsService;
import edu.citadel.liftingtrackerservice.transform.ResultsMapper;
import lombok.NonNull;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultsServiceImpl implements ResultsService {

    private final ResultsRepository resultsRepository;
    private final CompJoinRepository compJoinRepository;
    private final ResultsMapper resultsMapper = Mappers.getMapper(ResultsMapper.class);

    @Autowired
    public ResultsServiceImpl(@NonNull ResultsRepository resultsRepository,
                              @NonNull CompJoinRepository compJoinRepository){
        this.compJoinRepository = compJoinRepository;
        this.resultsRepository = resultsRepository;
    }


    @Override
    public ResultsDomain getResults(int memberId, int competitionId){
        CompJoin compJoin = compJoinRepository.findByCompetitionIdAndMemberId(competitionId, memberId);
        ResultsDomain fetchedResults = resultsMapper.resultsEntityToDomain(resultsRepository.findResultsByCompJoinId(compJoin.getCompJoinId()));
        return fetchedResults;
    }

    @Override
    public ResultsDomain updateResults(ResultsDomain resultsDomain, int memberId, int competitionId) {
        CompJoin compJoin = compJoinRepository.findByCompetitionIdAndMemberId(competitionId, memberId);
        Results convertedResults = resultsMapper.resultsDomainToEntity(resultsDomain);
        convertedResults.setCompJoinId(compJoin.getCompJoinId());
        Results updateResults = resultsRepository.save(convertedResults);
        return resultsMapper.resultsEntityToDomain(updateResults);
    }

    @Override
    public int persistResults(ResultsDomain resultsDomain, int memberId, int competitionId) {
        CompJoin compJoin = compJoinRepository.findByCompetitionIdAndMemberId(competitionId, memberId);
        Results convertedResults = resultsMapper.resultsDomainToEntity(resultsDomain);
        convertedResults.setCompJoinId(compJoin.getCompJoinId());
        Results savedResults = resultsRepository.save(convertedResults);
        return savedResults.getCompJoinId();
    }

}
