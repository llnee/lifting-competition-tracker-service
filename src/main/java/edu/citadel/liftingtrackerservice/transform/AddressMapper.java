package edu.citadel.liftingtrackerservice.transform;

import edu.citadel.liftingtrackerservice.api.model.AddressDomain;
import edu.citadel.liftingtrackerservice.api.model.CompetitorDomain;
import edu.citadel.liftingtrackerservice.dal.entity.Address;
import edu.citadel.liftingtrackerservice.dal.entity.Competitor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    Address addressDomainToEntity(AddressDomain addressDomain);
    AddressDomain addressEntityToDomain(Address address);

}
