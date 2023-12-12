package edu.citadel.liftingtrackerservice.api;

import edu.citadel.liftingtrackerservice.api.model.AddressDomain;
import edu.citadel.liftingtrackerservice.service.AddressService;
import edu.citadel.liftingtrackerservice.service.error.CompetitionServiceException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping(name = "/api/address")
@Tag(name = "Address", description = "Address API")
public class AddressApi {

    private final AddressService addressService;

    @Autowired
    public AddressApi(@NonNull AddressService addressService){
        this.addressService = addressService;
    }

    @Operation(summary = "Find Address by memberID", description = "Address search by %memberID% format", tags = { "address" })
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Address found",
            content = @Content(schema = @Schema(implementation = AddressDomain.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
            content = @Content(schema = @Schema(implementation = AddressDomain.class))),
            @ApiResponse(responseCode = "409", description = "Address not found",
            content = @Content(schema = @Schema(implementation = AddressDomain.class))),})
    @GetMapping(value = "address/{memberId}", produces = "application/json")
    public ResponseEntity<AddressDomain> findAddressByMemberId(@PathVariable int memberId){
        AddressDomain address;
        try {
            address = addressService.getAddress(memberId);
        }
        catch (CompetitionServiceException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(address);
    }

    @Operation(summary = "Add a new address", description = "Add a new address", tags = { "address" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Address created",
                    content = @Content(schema = @Schema(implementation = AddressDomain.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = AddressDomain.class))),
            @ApiResponse(responseCode = "409", description = "Address already exists",
                    content = @Content(schema = @Schema(implementation = AddressDomain.class))), })
    @PostMapping(value ="/address", consumes = {"application/json", "application/xml" }, produces = "application/json")
    public ResponseEntity<AddressDomain> createAddressByMemberId(
            @Parameter(description = "Address to add. Cannot null or empty.", required=true,
                    schema=@Schema(implementation = AddressDomain.class)) @Valid @RequestBody AddressDomain address){
        AddressDomain savedAddress;
        try {
            savedAddress = addressService.persistAddress(address);
        }
        catch (CompetitionServiceException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAddress);
    }

    @Operation(summary = "Update an Address", description = "Update an Address", tags = {"address"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Address Updated",
                    content = @Content(schema = @Schema(implementation = AddressDomain.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = AddressDomain.class))),
            @ApiResponse(responseCode = "409", description = "Can not be updated",
                    content = @Content(schema = @Schema(implementation = AddressDomain.class))),})
    @PatchMapping(value = "/address/update", consumes = {"application/json", "application/xml"}, produces = "application/json")
    public ResponseEntity<AddressDomain> updateAddress(
            @Parameter(description = "Update Address. Cannot null or empty.", required = true,
                    schema = @Schema(implementation = AddressDomain.class)) @Valid @RequestBody AddressDomain address) {
        AddressDomain updateAddress;
        try {
            updateAddress = addressService.updateAddress(address);
        } catch (CompetitionServiceException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(updateAddress);
    }
}

