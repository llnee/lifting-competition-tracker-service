package edu.citadel.liftingtrackerservice.transform;

import edu.citadel.liftingtrackerservice.api.model.CompetitionDomain;
import edu.citadel.liftingtrackerservice.dal.entity.Competition;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CompetitionMapper {

    CompetitionMapper INSTANCE = Mappers.getMapper(CompetitionMapper.class);

    Competition competitionDomainToEntity(CompetitionDomain competitionDomain);
    CompetitionDomain competitionEntityToDomain(Competition competition);

}
