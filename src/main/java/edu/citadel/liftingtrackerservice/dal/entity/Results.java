package edu.citadel.liftingtrackerservice.dal.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "RESULTS")
@Data
public class Results {

    @Id
    @Column(name = "COMP_JOIN_ID")
    private int compJoinId;

    @Column(name = "SNATCH_ATTEMPT1")
    private int snatchAttempt1;

    @Column(name = "SNATCH_ATTEMPT2")
    private int snatchAttempt2;

    @Column(name = "SNATCH_ATTEMPT3")
    private int snatchAttempt3;

    @Column(name = "CNJ_ATTEMPT1")
    private int cnjAttempt1;

    @Column(name = "CNJ_ATTEMPT2")
    private int cnjAttempt2;

    @Column(name = "CNJ_ATTEMPT3")
    private int cnjAttempt3;

}
