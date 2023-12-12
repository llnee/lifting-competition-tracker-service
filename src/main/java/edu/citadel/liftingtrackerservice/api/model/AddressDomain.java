package edu.citadel.liftingtrackerservice.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Address", description = "Address domain object")
public class AddressDomain {

    @Schema(description = "Member ID of member.",
            example = "123", required = true)
    private int memberId;
    @Schema(description = "Street address of competitor.",
             example = "111 Main St", required = true)
    private String addressOne;
    @Schema(description = "Apartment number of competitor.",
            example = "Apt 1")
    private String addressTwo;
    @Schema(description = "City of competitor's address.",
            example = "Charleston", required = true)
    private String city;
    @Schema(description = "State of competitor's address.",
            example = "SC", required = true)
    private String state;
    @Schema(description = "Zip code of competitor's address.",
            example = "29409", required = true)
    private int zip;

}
