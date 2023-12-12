package edu.citadel.liftingtrackerservice.dal;

import edu.citadel.liftingtrackerservice.dal.entity.Competitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetitorRepository extends JpaRepository<Competitor, Integer> {

    Competitor findCompetitorByMemberId(int memberId);

}
