package edu.citadel.liftingtrackerservice.service.impl;

import edu.citadel.liftingtrackerservice.api.model.CompetitorDomain;
import edu.citadel.liftingtrackerservice.dal.CompJoinRepository;
import edu.citadel.liftingtrackerservice.dal.CompetitorRepository;
import edu.citadel.liftingtrackerservice.dal.entity.CompJoin;
import edu.citadel.liftingtrackerservice.dal.entity.Competitor;
import edu.citadel.liftingtrackerservice.service.CompetitorService;
import edu.citadel.liftingtrackerservice.service.error.CompetitionServiceException;
import edu.citadel.liftingtrackerservice.transform.CompetitorMapper;
import lombok.NonNull;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompetitorServiceImpl implements CompetitorService {

    private final CompetitorRepository competitorRepository;
    private final CompJoinRepository compJoinRepository;
    private final CompetitorMapper competitorMapper = Mappers.getMapper(CompetitorMapper.class);

    @Autowired
    public CompetitorServiceImpl(@NonNull CompetitorRepository competitorRepository,
                                 @NonNull CompJoinRepository compJoinRepository){
        this.competitorRepository = competitorRepository;
        this.compJoinRepository = compJoinRepository;
    }
    @Override
    public CompetitorDomain getCompetitor(int memberId){
        CompetitorDomain fetchedCompetitor = competitorMapper.competitorEntityToDomain(competitorRepository.findCompetitorByMemberId(memberId));
        return fetchedCompetitor;
    }

    @Override
    public List<CompetitorDomain> getCompetitorsByCompetitionId(int competitionId) {
        List<CompJoin> fetchedCompetitors = compJoinRepository.findAllByCompetitionId(competitionId);
        List<CompetitorDomain> competitors = new ArrayList<>();
        for(CompJoin singleCompetitor : fetchedCompetitors){
            competitors.add(competitorMapper.competitorEntityToDomain(competitorRepository.findCompetitorByMemberId(singleCompetitor.getMemberId())));
        }
        return competitors;
    }

    @Override
    public CompetitorDomain persistCompetitor(CompetitorDomain competitorDomain) throws CompetitionServiceException {
        if (checkForExistingCompetitor(competitorDomain)){
            throw new CompetitionServiceException("Competitor already exists with member id " + competitorDomain.getMemberId());
        }
        Competitor convertedCompetitor = competitorMapper.competitorDomainToEntity(competitorDomain);
        Competitor savedCompetitor = competitorRepository.save(convertedCompetitor);
        return competitorMapper.competitorEntityToDomain(savedCompetitor);
    }

    private boolean checkForExistingCompetitor(CompetitorDomain competitorDomain) throws CompetitionServiceException {
        Competitor existingCompetitor = competitorRepository.findCompetitorByMemberId(competitorDomain.getMemberId());
        return existingCompetitor != null;
    }

    @Override
    public CompetitorDomain updateCompetitor(CompetitorDomain competitorDomain) throws CompetitionServiceException {
        if (!checkForExistingCompetitor(competitorDomain)){
            throw new CompetitionServiceException("No competitor exists with member id " + competitorDomain.getMemberId());
        }
        Competitor convertedCompetitor = competitorMapper.competitorDomainToEntity(competitorDomain);
        Competitor updateCompetitor = competitorRepository.save(convertedCompetitor);
        return competitorMapper.competitorEntityToDomain(updateCompetitor);
    }

}
