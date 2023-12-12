package edu.citadel.liftingtrackerservice.dal;

import edu.citadel.liftingtrackerservice.dal.entity.Results;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultsRepository extends JpaRepository<Results, Integer> {

    Results findResultsByCompJoinId(int compJoinId);

}
