package edu.citadel.liftingtrackerservice.api;

import edu.citadel.liftingtrackerservice.api.model.EmailDomain;
import edu.citadel.liftingtrackerservice.service.EmailService;
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

@RestController
@RequestMapping(name = "/api/email")
@Tag(name = "Email", description = "Email API")
public class EmailApi {
    private final EmailService emailService;

    @Autowired
    public EmailApi(@NonNull EmailService emailService) {
        this.emailService = emailService;
    }

    @Operation(summary = "Find Email by memberId", description = "Email search by %memberId% format", tags = {"email"})
    @ApiResponse(responseCode = "200", description = "successful operation",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = EmailDomain.class))))
    @GetMapping(value = "/Email/{memberId}", produces = "application/json")
    public ResponseEntity<EmailDomain> findEmailByMemberId(@PathVariable int memberId) {

        EmailDomain email = null;
        try {
            email = emailService.getEmail(memberId);
        } catch (CompetitionServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(email);
    }

    @Operation(summary = "Add a new email", description = "Add a new email", tags = {"email"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "email created",
                    content = @Content(schema = @Schema(implementation = EmailDomain.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "409", description = "Email already exists",
                    content = @Content(schema = @Schema(implementation = Void.class))),})
    @PostMapping(value = "/Email", consumes = {"application/json", "application/xml"}, produces = "application/json")
    public ResponseEntity<EmailDomain> createEmail(
            @Parameter(description = "Email to add. Cannot null or empty.", required = true,
                    schema = @Schema(implementation = EmailDomain.class)) @Valid @RequestBody EmailDomain email) {
        EmailDomain savedEmail = null;
        try {
            savedEmail = emailService.persistEmail(email);
        } catch (CompetitionServiceException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmail);

    }

    @Operation(summary = "Update an Email", description = "Update an Email", tags = {"email"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Email Updated",
                    content = @Content(schema = @Schema(implementation = EmailDomain.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "409", description = "Can not be updated",
                    content = @Content(schema = @Schema(implementation = Void.class))),})
    @PatchMapping(value = "/Email", consumes = {"application/json", "application/xml"}, produces = "application/json")
    public ResponseEntity<EmailDomain> UpdateEmail(
            @Parameter(description = "Update Email. Cannot null or empty.", required = true,
                    schema = @Schema(implementation = EmailDomain.class)) @Valid @RequestBody EmailDomain email) {
    EmailDomain updateEmail = null;
    try {
        updateEmail = emailService.updateEmail(email);
    } catch (CompetitionServiceException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }
    return ResponseEntity.status(HttpStatus.OK).body(updateEmail);
}
}



