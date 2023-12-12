package edu.citadel.liftingtrackerservice.dal.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "COMPETITION")
@Data
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int competitionId;

    @Column(name = "FACILITY_NAME")
    private String facilityName;

    @Column(name = "COMPETITION_DATE")
    private LocalDate compDate;

    @Column(name = "ADDRESS_ONE")
    private String addressOne;

    @Column(name = "ADDRESS_TWO")
    private String addressTwo;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    @Column(name = "ZIP")
    private int zip;

    @Column(name = "finalized")
    private boolean finalized;
}
