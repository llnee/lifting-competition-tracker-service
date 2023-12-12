package edu.citadel.liftingtrackerservice.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Email", description = "Email domain object")
public class EmailDomain {

    @Schema(description = "Email of Competitor.",
            example = "joedoe@gmail.com")
    private String email;

    @Schema(description = "Member id of the competitor",
    example = "123")
    private Integer memberId;

}
