package com.example.apitrocatinesql.controllers;

import com.example.apitrocatinesql.models.DTO.requestDTO.FindUserAddressRequestDTO;
import com.example.apitrocatinesql.models.DTO.requestDTO.SaveAddressRequestDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.FindProductCardResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.FindUserAddressResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.SaveAddressResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.StandardResponseDTO;
import com.example.apitrocatinesql.services.AddressService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@AllArgsConstructor
public class AddressController {
    private AddressService addressService;
    @GetMapping("/find-address")
    public StandardResponseDTO findAddressUser(@Valid @RequestBody FindUserAddressRequestDTO request){
        List<FindUserAddressResponseDTO> address = addressService.findUserAddress(request);
        return new StandardResponseDTO(false, address);
    }
    @PostMapping("/save-address")
    public StandardResponseDTO saveAddress(@Valid @RequestBody SaveAddressRequestDTO request){
        SaveAddressResponseDTO address = addressService.saveAddress(request);
        return new StandardResponseDTO(false, address);
    }
}
