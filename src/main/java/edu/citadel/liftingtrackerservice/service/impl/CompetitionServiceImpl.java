package edu.citadel.liftingtrackerservice.service.impl;

import edu.citadel.liftingtrackerservice.api.model.CompetitionDomain;
import edu.citadel.liftingtrackerservice.api.model.RegisterDomain;
import edu.citadel.liftingtrackerservice.dal.CompJoinRepository;
import edu.citadel.liftingtrackerservice.dal.CompetitorRepository;
import edu.citadel.liftingtrackerservice.dal.CompetitionRepository;
import edu.citadel.liftingtrackerservice.dal.ResultsRepository;
import edu.citadel.liftingtrackerservice.dal.entity.CompJoin;
import edu.citadel.liftingtrackerservice.dal.entity.Competitor;
import edu.citadel.liftingtrackerservice.dal.entity.Competition;
import edu.citadel.liftingtrackerservice.dal.entity.Results;
import edu.citadel.liftingtrackerservice.service.CompetitionService;
import edu.citadel.liftingtrackerservice.service.error.CompetitionServiceException;
import edu.citadel.liftingtrackerservice.transform.CompetitionMapper;
import edu.citadel.liftingtrackerservice.transform.RegisterMapper;
import lombok.NonNull;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;
    private final CompetitorRepository competitorRepository;
    private final CompJoinRepository compJoinRepository;
    private final ResultsRepository resultsRepository;
    private final CompetitionMapper competitionMapper = Mappers.getMapper(CompetitionMapper.class);
    private final RegisterMapper registerMapper = Mappers.getMapper(RegisterMapper.class);

    @Autowired
    public CompetitionServiceImpl(@NonNull CompetitionRepository competitionRepository,
                                  @NonNull CompetitorRepository competitorRepository,
                                  @NonNull CompJoinRepository compJoinRepository,
                                  @NonNull ResultsRepository resultsRepository){
        this.competitionRepository = competitionRepository;
        this.competitorRepository = competitorRepository;
        this.compJoinRepository = compJoinRepository;
        this.resultsRepository = resultsRepository;
    }
    @Override
    public CompetitionDomain getCompetition(int competitionId) throws CompetitionServiceException {
        CompetitionDomain fetchedCompetition = competitionMapper.competitionEntityToDomain(competitionRepository.findCompetitionByCompetitionId(competitionId));
        if (fetchedCompetition == null){
            throw new CompetitionServiceException("Competition does not exist for member id " + competitionId);
        }
        return fetchedCompetition;
    }

    @Override
    public CompetitionDomain persistCompetition(CompetitionDomain competitionDomain) throws CompetitionServiceException {
        if (competitionDomain.getCompetitionId() != null && checkForExistingCompetition(competitionDomain)){
            throw new CompetitionServiceException("Competition with id " + competitionDomain.getCompetitionId() + " does not exist!");
        }
        Competition convertedCompetition = competitionMapper.competitionDomainToEntity(competitionDomain);
        Competition savedCompetition = competitionRepository.save(convertedCompetition);
        return competitionMapper.competitionEntityToDomain(savedCompetition);
    }

    private boolean checkForExistingCompetition(CompetitionDomain competitionDomain) throws CompetitionServiceException {
        Competition existingCompetition = competitionRepository.findCompetitionByCompetitionId(competitionDomain.getCompetitionId());
        return existingCompetition != null;
    }

    private boolean checkForExistingCompetitor(Integer memberId){
        Competitor fetchedCompetitor = competitorRepository.findCompetitorByMemberId(memberId);
        return fetchedCompetitor != null;
    }

    @Override
    public CompetitionDomain updateCompetition(CompetitionDomain competitionDomain) throws CompetitionServiceException {
        if (!checkForExistingCompetition(competitionDomain)){
            throw new CompetitionServiceException("No email exists with member id " + competitionDomain.getCompetitionId());
        }
        Competition convertedCompetition = competitionMapper.competitionDomainToEntity(competitionDomain);
        Competition updateCompetition = competitionRepository.save(convertedCompetition);
        return competitionMapper.competitionEntityToDomain(updateCompetition);
    }

    @Override
    public void finalizeCompetition(int competitionId) {
        Competition retrievedCompetition = competitionRepository.findCompetitionByCompetitionId(competitionId);
        retrievedCompetition.setFinalized(true);
        competitionRepository.save(retrievedCompetition);
    }

    @Override
    public RegisterDomain addMemberToCompetition(RegisterDomain registerDomain) throws CompetitionServiceException {
        Competition competition = competitionRepository.findCompetitionByCompetitionId(registerDomain.getCompetitionId());
        if (competition == null){
            throw new CompetitionServiceException("No competition exists with competition id " + registerDomain.getCompetitionId());
        }
        Competitor competitor = competitorRepository.findCompetitorByMemberId(registerDomain.getMemberId());
        if (competitor == null){
            throw new CompetitionServiceException("No member exists with member id " + registerDomain.getMemberId());
        }
        CompJoin compJoin = compJoinRepository.findByCompetitionIdAndMemberId(registerDomain.getCompetitionId(), registerDomain.getMemberId());
        if (compJoin != null){
            throw new CompetitionServiceException("Member is already added to this competition");
        }

        CompJoin result = compJoinRepository.save(registerMapper.registerDomainToEntity(registerDomain));
        initializeNewCompetitorResultsRecord(result);
        return registerMapper.registerEntityToDomain(result);
    }

    private void initializeNewCompetitorResultsRecord(CompJoin result) {
        Results initializedResults = new Results();
        initializedResults.setCompJoinId(result.getCompJoinId());
        resultsRepository.save(initializedResults);
    }
}


