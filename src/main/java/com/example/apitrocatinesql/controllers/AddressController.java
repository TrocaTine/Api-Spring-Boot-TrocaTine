package com.example.apitrocatinesql.controllers;


import com.example.apitrocatinesql.exception.ExceptionHandlerDTO;
import com.example.apitrocatinesql.models.DTO.AddressDTO;
import com.example.apitrocatinesql.models.DTO.requestDTO.SaveAddressRequestDTO;

import com.example.apitrocatinesql.models.DTO.responseDTO.FindUserAddressResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.SaveAddressResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.StandardResponseDTO;
import com.example.apitrocatinesql.services.AddressService;
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
@RequestMapping("/address")
@AllArgsConstructor
@Tag(name = "Address controller", description = "Controller responsible for managing addresses")
public class AddressController {
    private AddressService addressService;
    @Operation(description = "Find total daily transactions amount by SellerID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Total daily transactions amount found", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AddressDTO.class)))
                    }),
                    @ApiResponse(responseCode = "400", description = "Invalid SellerID", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandlerDTO.class))}),
                    @ApiResponse(responseCode = "404", description = "No transactions found", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandlerDTO.class))}),
            }
    )
    @GetMapping("/find-address/{email}")
    public StandardResponseDTO findAddressUser(@Valid @PathVariable String email){
        List<FindUserAddressResponseDTO> address = addressService.findUserAddress(email);
        return new StandardResponseDTO(false, address);
    }
    @Operation(description = "Save an adress",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully returned list of addresses", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AddressDTO.class)))
                    }),
                    @ApiResponse(responseCode = "400", description = "Invalid SellerID", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandlerDTO.class))}),
                    @ApiResponse(responseCode = "404", description = "No transactions found", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandlerDTO.class))}),
            }
    )
    @PostMapping("/save-address")
    public StandardResponseDTO saveAddress(@Valid @RequestBody SaveAddressRequestDTO request){
        SaveAddressResponseDTO address = addressService.saveAddress(request);
        return new StandardResponseDTO(false, address);
    }
}
