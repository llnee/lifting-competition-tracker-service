package edu.citadel.liftingtrackerservice.api;

import edu.citadel.liftingtrackerservice.api.model.ResultsDomain;
import edu.citadel.liftingtrackerservice.api.model.ResultsPersistDomain;
import edu.citadel.liftingtrackerservice.service.ResultsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(name = "/api/results")
@Tag(name = "Results", description = "Results API")
public class ResultsApi {

    private final ResultsService resultsService;

    @Autowired
    public ResultsApi(@NonNull ResultsService resultsService) {
        this.resultsService = resultsService;
    }

    @Operation(summary = "Find a result by member and competition id", description = "Results search by %memberId% and %competitionId% format", tags = {"results"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Results found",
                    content = @Content(schema = @Schema(implementation = ResultsDomain.class))),
            @ApiResponse(responseCode = "400", description = "Invalid Input",
                    content = @Content(schema = @Schema(implementation = ResultsDomain.class))),
            @ApiResponse(responseCode = "409", description = "Competition Not Found",
                    content = @Content(schema = @Schema(implementation = ResultsDomain.class))),})
    @GetMapping(value = "/results/{memberId}/{competitionId}", produces = "application/json")
    public ResponseEntity<ResultsDomain> findResultsByCompJoinId(@PathVariable int memberId, @PathVariable int competitionId) {
        ResultsDomain results = resultsService.getResults(memberId, competitionId);
        return ResponseEntity.status(HttpStatus.CREATED).body(results);
    }

    @Operation(summary = "Add new results", description = "Add new results", tags = {"results"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Result created",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = ResultsDomain.class))),
            @ApiResponse(responseCode = "409", description = "Result already inputted",
                    content = @Content(schema = @Schema(implementation = ResultsDomain.class))),})
    @PostMapping(value = "/results/create", consumes = {"application/json", "application/xml"}, produces = "application/json")
    public ResponseEntity<String> createResultsById(
            @Parameter(description = "Results to add. Cannot null or empty.", required = true,
                    schema = @Schema(implementation = ResultsPersistDomain.class)) @Valid @RequestBody ResultsPersistDomain results) {
        int compJoinId = resultsService.persistResults(results.getResults(), results.getMemberId(), results.getCompetitionId());
        JSONObject resultsObject = new JSONObject();
        resultsObject.put("compJoinId", compJoinId);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultsObject.toJSONString());
    }

    @Operation(summary = "Update results", description = "Update results", tags = {"results"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Results successfully updated",
                    content = @Content(schema = @Schema(implementation = ResultsDomain.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = ResultsDomain.class))),
            @ApiResponse(responseCode = "409", description = "Results cannot be updated",
                    content = @Content(schema = @Schema(implementation = ResultsDomain.class))),})
    @PostMapping(value = "/results/update", consumes = {"application/json", "application/xml"}, produces = "application/json")
    public ResponseEntity<ResultsDomain> updateResults(
            @Parameter(description = "Results to update. Cannot null or empty.", required = true,
                    schema = @Schema(implementation = ResultsPersistDomain.class)) @Valid @RequestBody ResultsPersistDomain results) {
        ResultsDomain updateResults = resultsService.updateResults(results.getResults(), results.getMemberId(), results.getCompetitionId());
        return ResponseEntity.status(HttpStatus.CREATED).body(updateResults);
    }
}