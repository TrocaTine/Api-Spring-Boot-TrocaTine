package com.example.apitrocatinesql.controllers;


import com.example.apitrocatinesql.models.DTO.requestDTO.SaveInformactionCardRequestDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.FindCardUserResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.SaveInformactionCardResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.StandardResponseDTO;
import com.example.apitrocatinesql.services.CardService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
@AllArgsConstructor
public class CardController {

    private CardService cardService;

    @PostMapping("/save-informaction-card")
    public StandardResponseDTO saveInformactionCard(@RequestBody @Valid SaveInformactionCardRequestDTO requestDTO){
        SaveInformactionCardResponseDTO result = cardService.saveInformactionCard(requestDTO);
        return new StandardResponseDTO(false, result);
    }
    @GetMapping("/find-card-user/{email}")
    public StandardResponseDTO findCardByUser(@PathVariable @Valid String email){
        List<FindCardUserResponseDTO> result = cardService.findCardByUser(email);
        return new StandardResponseDTO(false, result);
    }

}
