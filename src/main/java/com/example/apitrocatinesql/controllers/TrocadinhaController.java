package com.example.apitrocatinesql.controllers;

import com.example.apitrocatinesql.models.DTO.requestDTO.AddingTrocadinhaRequestDTO;

import com.example.apitrocatinesql.models.DTO.requestDTO.RetiredTrocadinhaRequestDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.*;
import com.example.apitrocatinesql.services.TrocadinhaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trocadinha")
@AllArgsConstructor
public class TrocadinhaController {

    TrocadinhaService trocadinhaService;

    @GetMapping("/trocadinha-count/{email}")
    public StandardResponseDTO findTrocadinhaCount(@Valid @RequestBody String email){
        FindTrocadinhaCountResponseDTO findTrocadinhaCount = trocadinhaService.findTrocadinhaCount(email);
        return new StandardResponseDTO(false, findTrocadinhaCount);
    }
    @GetMapping("/ranking")
    public StandardResponseDTO findRankingTrocadinha(){
        List<FindRankingTrocadinhaResponseDTO> ranking = trocadinhaService.findRankingTrocadinha();
        return new StandardResponseDTO(false, ranking);
    }

    @PostMapping("/adding-trocadinha")
    public StandardResponseDTO addingTrocadinha(@Valid @RequestBody AddingTrocadinhaRequestDTO addingTrodinhaRequestDTO){
       AddingTrodinhaResponseDTO addingTrodinha = trocadinhaService.addingTrodinha(addingTrodinhaRequestDTO.email(), addingTrodinhaRequestDTO.amountTrocadinha());
        return new StandardResponseDTO(false, addingTrodinha);
    }

    @PostMapping("/retired-trocadinha")
    public StandardResponseDTO retiredTrocadinha(@Valid @RequestBody RetiredTrocadinhaRequestDTO retiredTrocadinhaRequestDTO){
        RetiredTrocadinhaResponseDTO retiredTrocadinha = trocadinhaService.retiredTrocadinha(retiredTrocadinhaRequestDTO.email(), retiredTrocadinhaRequestDTO.amountTrocadinha());
        return new StandardResponseDTO(false, retiredTrocadinha);
    }

}
