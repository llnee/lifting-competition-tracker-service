package edu.citadel.liftingtrackerservice.dal;

import edu.citadel.liftingtrackerservice.dal.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    Address findAddressByMemberId(int memberId);

}
