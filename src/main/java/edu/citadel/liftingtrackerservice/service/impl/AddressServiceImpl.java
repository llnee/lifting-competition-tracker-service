package edu.citadel.liftingtrackerservice.service.impl;

import edu.citadel.liftingtrackerservice.api.model.AddressDomain;
import edu.citadel.liftingtrackerservice.dal.AddressRepository;
import edu.citadel.liftingtrackerservice.dal.entity.Address;
import edu.citadel.liftingtrackerservice.dal.entity.Competitor;
import edu.citadel.liftingtrackerservice.dal.CompetitorRepository;
import edu.citadel.liftingtrackerservice.service.AddressService;
import edu.citadel.liftingtrackerservice.service.error.CompetitionServiceException;
import edu.citadel.liftingtrackerservice.transform.AddressMapper;
import lombok.NonNull;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper = Mappers.getMapper(AddressMapper.class);
    private final CompetitorRepository competitorRepository;

    @Autowired
    public AddressServiceImpl(@NonNull AddressRepository addressRepository,
                            @NonNull CompetitorRepository competitorRepository){
        this.addressRepository = addressRepository;
        this.competitorRepository = competitorRepository;
    }

    @Override
    public AddressDomain getAddress(int memberId) throws CompetitionServiceException {
        AddressDomain fetchedAddress = addressMapper.addressEntityToDomain(addressRepository.findAddressByMemberId(memberId));
        if (fetchedAddress == null){
            throw new CompetitionServiceException("Address does not exist for member id " + memberId);
        }
        return fetchedAddress;
    }

    @Override
    public AddressDomain persistAddress(AddressDomain addressDomain) throws CompetitionServiceException {
        if (!checkForExistingCompetitor(addressDomain.getMemberId())){
            throw new CompetitionServiceException("Competitor with id " + addressDomain.getMemberId() + " does not exist.");
        }
        Address convertedAddress = addressMapper.addressDomainToEntity(addressDomain);
        Address savedAddress = addressRepository.save(convertedAddress);
        return addressMapper.addressEntityToDomain(savedAddress);
    }

    @Override
    public AddressDomain updateAddress(AddressDomain addressDomain) throws CompetitionServiceException {
        if (!checkForExistingAddress(addressDomain)){
            throw new CompetitionServiceException("No address exists with member id " + addressDomain.getMemberId());
        }
        Address convertedAddress = addressMapper.addressDomainToEntity(addressDomain);
        Address updateAddress = addressRepository.save(convertedAddress);
        return addressMapper.addressEntityToDomain(updateAddress);
    }

    private boolean checkForExistingCompetitor(Integer memberId) {
        Competitor fetchedCompetitor = competitorRepository.findCompetitorByMemberId(memberId);
        return fetchedCompetitor != null;
    }

    private boolean checkForExistingAddress(AddressDomain addressDomain) {
        Address existingAddress = addressRepository.findAddressByMemberId(addressDomain.getMemberId());
        return existingAddress != null;
    }
}
