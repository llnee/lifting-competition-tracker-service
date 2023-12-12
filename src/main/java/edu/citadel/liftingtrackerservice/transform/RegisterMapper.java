package edu.citadel.liftingtrackerservice.transform;

import edu.citadel.liftingtrackerservice.api.model.RegisterDomain;
import edu.citadel.liftingtrackerservice.dal.entity.CompJoin;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RegisterMapper {

    RegisterMapper INSTANCE = Mappers.getMapper(RegisterMapper.class);

    CompJoin registerDomainToEntity(RegisterDomain registerDomain);
    RegisterDomain registerEntityToDomain(CompJoin compJoin);

}
