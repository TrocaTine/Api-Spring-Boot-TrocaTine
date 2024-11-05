package com.example.apitrocatinesql.controllers;



import com.example.apitrocatinesql.models.DTO.requestDTO.SavePushRequestDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.FindPushByUserResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.SavePushResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.StandardResponseDTO;
import com.example.apitrocatinesql.services.PushService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/notification")
@AllArgsConstructor
@Tag(name = "Notification Controller", description = "Controller responsible for managing notifications")
public class PushController {

    private PushService pushService;

    @Operation(summary = "Find notifications by user email", description = "Retrieves a list of notifications for the specified user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved notifications"),
            @ApiResponse(responseCode = "404", description = "User not found or no notifications available"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/find-notification-user/{email}")
    public StandardResponseDTO findNotificationByUser(@Valid @PathVariable String email){
        List<FindPushByUserResponseDTO> listNotifications = pushService.findPushByUser(email);
        return new StandardResponseDTO(false, listNotifications);
    }

    @Operation(summary = "Save notification", description = "Saves a new notification for the user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully saved notification"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/save-notification")
    public StandardResponseDTO saveNotification(@Valid @RequestBody SavePushRequestDTO request){
        SavePushResponseDTO save = pushService.savePush(request);
        return new StandardResponseDTO(false, save);
    }
}
