package edu.citadel.liftingtrackerservice.service.impl;

import edu.citadel.liftingtrackerservice.api.model.EmailDomain;
import edu.citadel.liftingtrackerservice.dal.CompetitorRepository;
import edu.citadel.liftingtrackerservice.dal.EmailRepository;
import edu.citadel.liftingtrackerservice.dal.entity.Competitor;
import edu.citadel.liftingtrackerservice.dal.entity.Email;
import edu.citadel.liftingtrackerservice.service.EmailService;
import edu.citadel.liftingtrackerservice.service.error.CompetitionServiceException;
import edu.citadel.liftingtrackerservice.transform.EmailMapper;
import lombok.NonNull;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;
    private final CompetitorRepository competitorRepository;
    private final EmailMapper emailMapper = Mappers.getMapper(EmailMapper.class);

    @Autowired
    public EmailServiceImpl(@NonNull EmailRepository emailRepository,
                            @NonNull CompetitorRepository competitorRepository){
        this.emailRepository = emailRepository;
        this.competitorRepository = competitorRepository;
    }
    @Override
    public EmailDomain getEmail(int memberId) throws CompetitionServiceException {
        EmailDomain fetchedEmail = emailMapper.emailEntityToDomain(emailRepository.findByMemberId(memberId));
        if (fetchedEmail == null){
            throw new CompetitionServiceException("Email does not exist for member id " + memberId);
        }
        return fetchedEmail;
    }

    @Override
    public EmailDomain persistEmail(EmailDomain emailDomain) throws CompetitionServiceException {
        if (!checkForExistingCompetitor(emailDomain.getMemberId())){
            throw new CompetitionServiceException("Competitor with id " + emailDomain.getMemberId() + " does not exist!");
        }
        Email convertedEmail = emailMapper.emailDomainToEntity(emailDomain);
        Email savedEmail = emailRepository.save(convertedEmail);
        return emailMapper.emailEntityToDomain(savedEmail);
    }

    private boolean checkForExistingEmail(EmailDomain emailDomain) throws CompetitionServiceException {
        Email existingEmail = emailRepository.findByMemberId(emailDomain.getMemberId());
        return existingEmail != null;
    }

    private boolean checkForExistingCompetitor(Integer memberId){
        Competitor fetchedCompetitor = competitorRepository.findCompetitorByMemberId(memberId);
        return fetchedCompetitor != null;
    }

    @Override
    public EmailDomain updateEmail(EmailDomain emailDomain) throws CompetitionServiceException {
        if (!checkForExistingEmail(emailDomain)){
            throw new CompetitionServiceException("No email exists with member id " + emailDomain.getMemberId());
        }
        Email convertedEmail = emailMapper.emailDomainToEntity(emailDomain);
        Email updateEmail = emailRepository.save(convertedEmail);
        return emailMapper.emailEntityToDomain(updateEmail);
    }
    }


