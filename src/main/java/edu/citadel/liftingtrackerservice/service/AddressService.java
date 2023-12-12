package edu.citadel.liftingtrackerservice.service;

import edu.citadel.liftingtrackerservice.api.model.AddressDomain;
import edu.citadel.liftingtrackerservice.service.error.CompetitionServiceException;

public interface AddressService {

    AddressDomain persistAddress(AddressDomain addressDomain) throws CompetitionServiceException;
    AddressDomain getAddress(int memberId) throws CompetitionServiceException;
    AddressDomain updateAddress(AddressDomain addressDomain) throws CompetitionServiceException;

}
