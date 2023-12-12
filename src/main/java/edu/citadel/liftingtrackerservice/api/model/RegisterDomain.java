package edu.citadel.liftingtrackerservice.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Register", description = "Registration domain object")
public class RegisterDomain {

    @Schema(description = "Competition id of the competition",
            example = "123")
    private Integer competitionId;

    @Schema(description = "Member id of the competitor",
            example = "123")
    private Integer memberId;

}
