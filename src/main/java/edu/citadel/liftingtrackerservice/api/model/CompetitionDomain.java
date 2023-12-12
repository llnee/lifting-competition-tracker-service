package edu.citadel.liftingtrackerservice.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Competition", description = "Competition domain object")
public class CompetitionDomain {

    @Schema(description = "Facility name of competition.",
            example = "USA Gym", required = true)
    private String facilityName;
    @Schema(description = "Date of competition.",
            example = "2021-10-10", required = true)
    private LocalDate compDate;
    @Schema(description = "Address of competition.",
            example = "124 Green St", required = true)
    private String addressOne;
    @Schema(description = "Address (2) of competition.",
            example = "Apt 3445")
    private String addressTwo;
    @Schema(description = "City of competition.",
            example = "Charleston", required = true)
    private String city;
    @Schema(description = "State of competition.",
            example = "South Carolina", required = true)
    private String state;
    @Schema(description = "Zip code of competition.",
            example = "49283", required = true)
    private Integer zip;
    @Schema(description = "Requested competition ID.  If blank a random ID will be assigned",
            example = "123")
    private Integer competitionId;
}
