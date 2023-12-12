package edu.citadel.liftingtrackerservice.api;

import edu.citadel.liftingtrackerservice.api.model.CompetitionDomain;
import edu.citadel.liftingtrackerservice.api.model.RegisterDomain;
import edu.citadel.liftingtrackerservice.service.CompetitionService;
import edu.citadel.liftingtrackerservice.service.error.CompetitionServiceException;
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
@RequestMapping(name = "/api/competition")
@Tag(name = "Competition", description = "Competition API")
public class CompetitionApi {

    private final CompetitionService competitionService;

    @Autowired
    public CompetitionApi(@NonNull CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @Operation(summary = "Find a competition by memberId", description = "Competition search by %memberId% format", tags = {"competition"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Competition found",
                    content = @Content(schema = @Schema(implementation = CompetitionDomain.class))),
            @ApiResponse(responseCode = "400", description = "Invalid Input",
                    content = @Content(schema = @Schema(implementation = CompetitionDomain.class))),
            @ApiResponse(responseCode = "409", description = "Competition Not Found",
                    content = @Content(schema = @Schema(implementation = CompetitionDomain.class))),})
    @GetMapping(value = "/Competition/{memberId}", produces = "application/json")
    public ResponseEntity<CompetitionDomain> findCompetitionByMemberId(@PathVariable int memberId) {

        CompetitionDomain competition = null;
        try {
            competition = competitionService.getCompetition(memberId);
        } catch (CompetitionServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(competition);
    }

    @Operation(summary = "Finalize a competition", description = "Competition finalization by %competitionId% format", tags = {"competition"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Competition found",
                    content = @Content(schema = @Schema(implementation = CompetitionDomain.class))),
            @ApiResponse(responseCode = "400", description = "Invalid Input",
                    content = @Content(schema = @Schema(implementation = CompetitionDomain.class))),
            @ApiResponse(responseCode = "409", description = "Competition Not Found",
                    content = @Content(schema = @Schema(implementation = CompetitionDomain.class))),})
    @GetMapping(value = "/Competition/finalize/{competition}", produces = "application/json")
    public ResponseEntity<String> finalizeCompetitionByCompId(@PathVariable int competitionId) {



        return ResponseEntity.status(HttpStatus.OK).body("Complete");
    }

    @Operation(summary = "Add a new competition", description = "Add a new competition", tags = {"competition"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Competition created",
                    content = @Content(schema = @Schema(implementation = CompetitionDomain.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = CompetitionDomain.class))),
            @ApiResponse(responseCode = "409", description = "Competition already exists",
                    content = @Content(schema = @Schema(implementation = CompetitionDomain.class))),})
    @PostMapping(value = "/Competition", consumes = {"application/json", "application/xml"}, produces = "application/json")
    public ResponseEntity<Object> createCompetition(
            @Parameter(description = "Competition to add. Cannot null or empty.", required = true,
                    schema = @Schema(implementation = CompetitionDomain.class)) @Valid @RequestBody CompetitionDomain competition) {
        CompetitionDomain savedCompetition = null;
        try {
            savedCompetition = competitionService.persistCompetition(competition);
        } catch (CompetitionServiceException e) {
            JSONObject error = new JSONObject();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCompetition);

    }

    @Operation(summary = "Update a competition", description = "Update a competition", tags = {"competition"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Competition successfully updated",
                    content = @Content(schema = @Schema(implementation = CompetitionDomain.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = CompetitionDomain.class))),
            @ApiResponse(responseCode = "409", description = "Competition cannot be updated",
                    content = @Content(schema = @Schema(implementation = CompetitionDomain.class))),})
    @PatchMapping(value = "/Competition", consumes = {"application/json", "application/xml"}, produces = "application/json")
    public ResponseEntity<Object> UpdateCompetition( @Parameter(description = "Update Competition. Cannot null or empty.", required = true,
                    schema = @Schema(implementation = CompetitionDomain.class)) @Valid @RequestBody CompetitionDomain competition) {
        CompetitionDomain updateCompetition = null;
        try {
            updateCompetition = competitionService.updateCompetition(competition);
        } catch (CompetitionServiceException e) {
            JSONObject error = new JSONObject();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }
        return ResponseEntity.status(HttpStatus.OK).body(updateCompetition);
    }

    @Operation(summary = "Add competitor to a competition", description = "Add a competitor to a competition", tags = {"competition"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Competition successfully updated",
                    content = @Content(schema = @Schema(implementation = RegisterDomain.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = RegisterDomain.class))),
            @ApiResponse(responseCode = "409", description = "Competition cannot be updated",
                    content = @Content(schema = @Schema(implementation = RegisterDomain.class))),})
    @PostMapping(value = "/Competition/register", consumes = {"application/json", "application/xml"}, produces = "application/json")
    public ResponseEntity<Object> registerCompetitor(
            @Parameter(description = "Update Competition. Cannot null or empty.", required = true,
                    schema = @Schema(implementation = RegisterDomain.class)) @Valid @RequestBody RegisterDomain registerDomain) {
        RegisterDomain successfulResult = null;
        try {
            successfulResult = competitionService.addMemberToCompetition(registerDomain);
        } catch (CompetitionServiceException e){
            JSONObject error = new JSONObject();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }
        return ResponseEntity.status(HttpStatus.OK).body(successfulResult);
    }
}