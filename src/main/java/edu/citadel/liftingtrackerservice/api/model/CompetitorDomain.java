package edu.citadel.liftingtrackerservice.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Competitor", description = "Competitor domain object")
public class CompetitorDomain {

    @Schema(description = "First name of competitor.",
            example = "Bobby")
    private String firstName;
    @Schema(description = "Last name of competitor.",
            example = "McLiftsALot", required = true)
    private String lastName;
    @Schema(description = "Birth date of competitor.",
            example = "12/31/1921", required = true)
    private LocalDate birthDate;
    @Schema(description = "Weight of competitor.",
            example = "123", required = true)
    private long weight;
    @Schema(description = "Gender of competitor.",
            example = "Male", required = true)
    private String gender;
    @Schema(description = "Member ID of competitor.",
            example = "123")
    private Integer memberId;
}
