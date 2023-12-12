package edu.citadel.liftingtrackerservice.dal;

import edu.citadel.liftingtrackerservice.dal.entity.CompJoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompJoinRepository extends JpaRepository<CompJoin, Integer> {

    List<CompJoin> findAllByCompetitionId(Integer competitionId);
    List<CompJoin> findAllByMemberId(Integer memberId);
    CompJoin findByCompetitionIdAndMemberId(Integer competitionId, Integer memberId);

}
