package edu.citadel.liftingtrackerservice.transform;

import edu.citadel.liftingtrackerservice.api.model.CompetitorDomain;
import edu.citadel.liftingtrackerservice.dal.entity.Competitor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CompetitorMapper {

    CompetitorMapper INSTANCE = Mappers.getMapper(CompetitorMapper.class);

    Competitor competitorDomainToEntity(CompetitorDomain competitorDomain);
    CompetitorDomain competitorEntityToDomain(Competitor competitor);

}
