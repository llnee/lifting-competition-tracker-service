package edu.citadel.liftingtrackerservice.transform;

import edu.citadel.liftingtrackerservice.api.model.EmailDomain;
import edu.citadel.liftingtrackerservice.dal.entity.Email;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmailMapper {

    EmailMapper INSTANCE = Mappers.getMapper(EmailMapper.class);

    Email emailDomainToEntity(EmailDomain emailDomain);
    EmailDomain emailEntityToDomain(Email email);

}
