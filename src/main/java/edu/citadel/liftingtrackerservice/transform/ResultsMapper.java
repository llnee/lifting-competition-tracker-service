package edu.citadel.liftingtrackerservice.transform;

import edu.citadel.liftingtrackerservice.api.model.ResultsDomain;
import edu.citadel.liftingtrackerservice.dal.entity.Results;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ResultsMapper {

    ResultsMapper INSTANCE = Mappers.getMapper(ResultsMapper.class);

    Results resultsDomainToEntity(ResultsDomain resultsDomain);
    ResultsDomain resultsEntityToDomain(Results results);

}
