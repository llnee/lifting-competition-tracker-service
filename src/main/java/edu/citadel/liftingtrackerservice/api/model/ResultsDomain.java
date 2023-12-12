package edu.citadel.liftingtrackerservice.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Results", description = "Results domain object")
public class ResultsDomain {

    @Schema(description = "Snatch attempt 1.",
            example = "999", required = true)
    private int snatchAttempt1;
    @Schema(description = "Snatch attempt 2.",
            example = "29", required = true)
    private int snatchAttempt2;
    @Schema(description = "Snatch attempt 3.",
            example = "2", required = true)
    private int snatchAttempt3;
    @Schema(description = "Clean and Jerk attempt 1.",
            example = "0")
    private int cnjAttempt1;
    @Schema(description = "Clean and Jerk attempt 2.",
            example = "93", required = true)
    private int cnjAttempt2;
    @Schema(description = "Clean and Jerk attempt 3.",
            example = "922", required = true)
    private int cnjAttempt3;

}
