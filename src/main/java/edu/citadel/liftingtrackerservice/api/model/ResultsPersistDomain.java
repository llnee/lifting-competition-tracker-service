package edu.citadel.liftingtrackerservice.api.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "ResultsUpdate", description = "Results update request domain object")
public class ResultsPersistDomain {

    private ResultsDomain results;
    private int memberId;
    private int competitionId;
}
