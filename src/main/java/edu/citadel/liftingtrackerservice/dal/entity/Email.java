package edu.citadel.liftingtrackerservice.dal.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "EMAIL")
@Data
public class Email {

    @Id
    private int memberId;

    @Column(name = "EMAIL")
    private String email;

}
