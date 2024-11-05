package com.example.apitrocatinesql.controllers;

import com.example.apitrocatinesql.models.DTO.requestDTO.AddingTrocadinhaRequestDTO;

import com.example.apitrocatinesql.models.DTO.requestDTO.RetiredTrocadinhaRequestDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.*;
import com.example.apitrocatinesql.services.TrocadinhaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;


@RestController
@RequestMapping("/trocadinha")
@AllArgsConstructor
@Tag(name = "Trocadinha Controller", description = "Controller responsible for managing virtual currency 'trocadinhas'")
public class TrocadinhaController {

    private TrocadinhaService trocadinhaService;

    @Operation(summary = "Get trocadinha count", description = "Retrieves the total count of trocadinhas for the specified user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved trocadinha count"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/trocadinha-count/{email}")
    public StandardResponseDTO findTrocadinhaCount(@Valid @PathVariable String email){
        FindTrocadinhaCountResponseDTO findTrocadinhaCount = trocadinhaService.findTrocadinhaCount(email);
        return new StandardResponseDTO(false, findTrocadinhaCount);
    }

    @Operation(summary = "Get ranking of trocadinhas", description = "Retrieves the ranking of users based on their trocadinha counts.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved ranking"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/ranking")
    public StandardResponseDTO findRankingTrocadinha(){
        List<FindRankingTrocadinhaResponseDTO> ranking = trocadinhaService.findRankingTrocadinha();
        return new StandardResponseDTO(false, ranking);
    }

    @Operation(summary = "Add trocadinhas", description = "Adds a specified amount of trocadinhas to the user's account.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added trocadinhas"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/adding-trocadinha")
    public StandardResponseDTO addingTrocadinha(@Valid @RequestBody AddingTrocadinhaRequestDTO addingTrocadinhaRequestDTO){
        AddingTrodinhaResponseDTO addingTrocadinha = trocadinhaService.addingTrodinha(addingTrocadinhaRequestDTO.email(), addingTrocadinhaRequestDTO.amountTrocadinha());
        return new StandardResponseDTO(false, addingTrocadinha);
    }

    @Operation(summary = "Retire trocadinhas", description = "Withdraws a specified amount of trocadinhas from the user's account.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully withdrew trocadinhas"),
            @ApiResponse(responseCode = "404", description = "User not found or insufficient trocadinhas"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/retired-trocadinha")
    public StandardResponseDTO retiredTrocadinha(@Valid @RequestBody RetiredTrocadinhaRequestDTO retiredTrocadinhaRequestDTO){
        RetiredTrocadinhaResponseDTO retiredTrocadinha = trocadinhaService.retiredTrocadinha(retiredTrocadinhaRequestDTO.email(), retiredTrocadinhaRequestDTO.amountTrocadinha());
        return new StandardResponseDTO(false, retiredTrocadinha);
    }
}
