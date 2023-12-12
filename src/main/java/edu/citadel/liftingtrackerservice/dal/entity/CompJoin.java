package edu.citadel.liftingtrackerservice.dal.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "COMPETITION_JOIN")
@Data
public class CompJoin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int compJoinId;

    @Column(name = "COMPETITION_ID")
    private int competitionId;

    @Column(name = "MEMBER_ID")
    private int memberId;

}
