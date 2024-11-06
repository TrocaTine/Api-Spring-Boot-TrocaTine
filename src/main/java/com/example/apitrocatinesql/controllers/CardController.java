package com.example.apitrocatinesql.controllers;


import com.example.apitrocatinesql.models.DTO.requestDTO.SaveInformactionCardRequestDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.FindCardUserResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.SaveInformactionCardResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.StandardResponseDTO;
import com.example.apitrocatinesql.services.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/card")
@AllArgsConstructor
@Tag(name = "Product Card Controller", description = "Controller responsible for managing product cards")
public class CardController {

    private CardService cardService;

    @Operation(summary = "Save product card information", description = "Saves information about the product card.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully saved product card information", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = SaveInformactionCardResponseDTO.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDTO.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDTO.class))
            }),
            @ApiResponse(responseCode = "409", description = "Conflict: User not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDTO.class))
            })
    })
    @PostMapping("/save-informaction-card")
    public StandardResponseDTO saveInformactionCard(@RequestBody @Valid SaveInformactionCardRequestDTO requestDTO) {
        SaveInformactionCardResponseDTO result = cardService.saveInformactionCard(requestDTO);
        return new StandardResponseDTO(false, result);
    }

    @Operation(summary = "Find product card by user email", description = "Returns a list of all product cards associated with the user's email.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned list of product cards", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = FindCardUserResponseDTO.class)))
            }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDTO.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDTO.class))
            }),
            @ApiResponse(responseCode = "409", description = "Conflict: User not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDTO.class))
            })
    })
    @GetMapping("/find-card-user/{email}")
    public StandardResponseDTO findCardByUser(@PathVariable @Valid String email) {
        List<FindCardUserResponseDTO> result = cardService.findCardByUser(email);
        return new StandardResponseDTO(false, result);
    }
}