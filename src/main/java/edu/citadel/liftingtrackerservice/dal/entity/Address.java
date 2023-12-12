package edu.citadel.liftingtrackerservice.dal.entity;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "ADDRESS")
@Data
public class Address {

    @Id
    private int memberId;

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
}
