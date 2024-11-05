package com.example.apitrocatinesql.controllers;


import com.example.apitrocatinesql.models.DTO.requestDTO.SaveAddressRequestDTO;

import com.example.apitrocatinesql.models.DTO.responseDTO.FindUserAddressResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.SaveAddressResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.StandardResponseDTO;
import com.example.apitrocatinesql.services.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@AllArgsConstructor
@Tag(name = "Address controller", description = "Controller responsible for managing addresses")
public class AddressController {
    private AddressService addressService;
    @Operation(summary = "Find all adresses by email", description = "Returns a list of all addresses.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned list of addresses"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "409", description = "User not found")
    })
    @GetMapping("/find-address/{email}")
    public StandardResponseDTO findAddressUser(@Valid @PathVariable String email){
        List<FindUserAddressResponseDTO> address = addressService.findUserAddress(email);
        return new StandardResponseDTO(false, address);
    }
    @Operation(summary = "Save an adress", description = "Return if the address was save.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned list of addresses"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "409", description = "User not found")
    })
    @PostMapping("/save-address")
    public StandardResponseDTO saveAddress(@Valid @RequestBody SaveAddressRequestDTO request){
        SaveAddressResponseDTO address = addressService.saveAddress(request);
        return new StandardResponseDTO(false, address);
    }
}
