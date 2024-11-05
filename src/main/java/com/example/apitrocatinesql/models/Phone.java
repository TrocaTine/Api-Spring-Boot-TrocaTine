package com.example.apitrocatinesql.models;

import io.swagger.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Schema(description = "User phone details, including phone number and associated user.")
@Table(name = "phones")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_phone")
    @Schema(description = "Unique identifier for the phone", example = "1")
    private Long idPhone;

    @Column(name = "number", nullable = false)
    @NotBlank(message = "Phone number is required.")
    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 characters.")
    @Pattern(regexp = "\\d{10,15}", message = "Phone number should contain only digits.")
    @Schema(description = "User's phone number", example = "1234567890")
    private String number;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    @Schema(description = "User associated with this phone number")
    private User user;

    public Phone(String number, User user) {
        this.number = number;
        this.user = user;
    }
}
