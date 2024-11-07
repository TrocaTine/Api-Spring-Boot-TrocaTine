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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
@Tag(name = "Order Controller", description = "Controller responsible for managing product orders")
public class OrderController {

    private OrderService orderService;

    @Operation(summary = "Finalize product order", description = "Completes the order process for a product.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully finalized the order", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = FinishedOrderResponseDTO.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDTO.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDTO.class))
            }),
            @ApiResponse(responseCode = "409", description = "Order could not be processed", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDTO.class))
            })
    })
    @PostMapping("/finished-order")
    public StandardResponseDTO finishedOrder(@Valid @RequestBody FinishedOrderRequestDTO request) {
        FinishedOrderResponseDTO result = orderService.finallyOrder(request);
        return new StandardResponseDTO(false, result);
    }
}
