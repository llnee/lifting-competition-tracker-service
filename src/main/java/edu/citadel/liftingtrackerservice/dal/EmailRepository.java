package edu.citadel.liftingtrackerservice.dal;

import edu.citadel.liftingtrackerservice.dal.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<Email, Integer> {

    Email findByMemberId(int memberId);

}
