package com.example.apitrocatinesql.controllers;


import com.example.apitrocatinesql.models.DTO.requestDTO.FinishedOrderRequestDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.FinishedOrderResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.StandardResponseDTO;
import com.example.apitrocatinesql.services.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    @PostMapping("/finished-order")
    public StandardResponseDTO finishedOrder(@Valid @RequestBody FinishedOrderRequestDTO request){
        FinishedOrderResponseDTO result = orderService.finallyOrder(request);
        return new StandardResponseDTO(false, result);
    }


}
