package edu.citadel.liftingtrackerservice.dal.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "COMPETITOR")
@Data
public class Competitor {

    @Id
    private int memberId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "BIRTH_DATE")
    private LocalDate birthDate;

    @Column(name = "WEIGHT")
    private Long weight;

    @Column(name = "GENDER")
    private String gender;

}
