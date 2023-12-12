package edu.citadel.liftingtrackerservice.api;

import edu.citadel.liftingtrackerservice.api.model.CompetitorDomain;
import edu.citadel.liftingtrackerservice.service.CompetitorService;
import edu.citadel.liftingtrackerservice.service.error.CompetitionServiceException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(name = "/api/competitor")
@Tag(name = "Competitor", description = "Competitor API")
public class CompetitorApi {
    private final CompetitorService competitorService;

    @Autowired
    public CompetitorApi(@NonNull CompetitorService competitorService) {
        this.competitorService = competitorService;
    }

    @Operation(summary = "Find Competitor by memberId", description = "Competitor search by %memberId% format", tags = {"competitor"})
    @ApiResponse(responseCode = "200", description = "successful operation",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = CompetitorDomain.class))))
    @GetMapping(value = "/competitor/{memberId}", produces = "application/json")
    public ResponseEntity<CompetitorDomain> findCompetitorById(@PathVariable int memberId) {

        CompetitorDomain competitor = competitorService.getCompetitor(memberId);
        return ResponseEntity.status(HttpStatus.CREATED).body(competitor);
    }

    @Operation(summary = "Find Competitors by competition ID", description = "Competitor search by %compId% format", tags = {"competitor"})
    @ApiResponse(responseCode = "200", description = "successful operation",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = CompetitorDomain.class))))
    @GetMapping(value = "/competitor/competition/{compId}", produces = "application/json")
    public ResponseEntity<List<CompetitorDomain>> findCompetitorsByCompetitionId(@PathVariable int compId) {

        List<CompetitorDomain> competitor = competitorService.getCompetitorsByCompetitionId(compId);
        return ResponseEntity.status(HttpStatus.CREATED).body(competitor);
    }

    @Operation(summary = "Add a new competitor", description = "Add a new competitor", tags = {"competitor"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Contact created",
                    content = @Content(schema = @Schema(implementation = CompetitorDomain.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "409", description = "Contact already exists",
                    content = @Content(schema = @Schema(implementation = Void.class))),})
    @PostMapping(value = "/competitor", consumes = {"application/json", "application/xml"}, produces = "application/json")
    public ResponseEntity<CompetitorDomain> createCompetitorById(
            @Parameter(description = "Competitor to add. Cannot null or empty.", required = true,
                    schema = @Schema(implementation = CompetitorDomain.class)) @Valid @RequestBody CompetitorDomain competitor) {

        CompetitorDomain savedCometitor = null;
        try {
            savedCometitor = competitorService.persistCompetitor(competitor);
        } catch (CompetitionServiceException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCometitor);

    }

    @Operation(summary = "Update a Competitor", description = "Update a Competitor", tags = {"competitor"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contact Updated",
                    content = @Content(schema = @Schema(implementation = CompetitorDomain.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "409", description = "Can not be updated",
                    content = @Content(schema = @Schema(implementation = Void.class))),})

    @PatchMapping(value = "/competitor", consumes = {"application/json", "application/xml"}, produces = "application/json")
    public ResponseEntity<CompetitorDomain> UpdateCompetitorById(
            @Parameter(description = "Update Competitor. Cannot null or empty.", required = true,
                    schema = @Schema(implementation = CompetitorDomain.class)) @Valid @RequestBody CompetitorDomain competitor) {
        CompetitorDomain updateCompetitor = null;
        try {
            updateCompetitor = competitorService.updateCompetitor(competitor);
        } catch (CompetitionServiceException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(updateCompetitor);
    }
}



