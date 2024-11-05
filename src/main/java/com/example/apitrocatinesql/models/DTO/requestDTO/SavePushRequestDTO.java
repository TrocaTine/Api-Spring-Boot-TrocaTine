package com.example.apitrocatinesql.models.DTO.requestDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Schema(description = "Data Transfer Object for creating a push notification")
public record SavePushRequestDTO(

        @NotNull(message = "Title cannot be null.")
        @Schema(description = "Title of the push notification", example = "New Features Available!")
        String title,

        @NotNull(message = "Description cannot be null.")
        @Schema(description = "Detailed description of the push notification", example = "Check out the latest updates to our app.")
        String description,

        @NotNull(message = "List of emails cannot be null.")
        @Schema(description = "List of user emails to receive the notification", example = "[\"user1@example.com\", \"user2@example.com\"]")
        List<String> email
) {
}
