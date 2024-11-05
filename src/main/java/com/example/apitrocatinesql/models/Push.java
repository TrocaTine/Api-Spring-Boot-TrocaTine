package com.example.apitrocatinesql.models;

import io.swagger.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Schema(description = "Represents a notification push, including its title, description, and associated users.")
@Table(name = "push")
public class Push {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_push")
    @Schema(description = "Unique identifier for the push notification", example = "1")
    private Long idPush;

    @Column(name = "title", nullable = false)
    @NotBlank(message = "Push title is required.")
    @Size(max = 100, message = "Push title must be less than or equal to 100 characters.")
    @Schema(description = "Title of the push notification", example = "New Feature Alert!")
    private String title;

    @Column(name = "description", nullable = false)
    @NotBlank(message = "Push description is required.")
    @Size(max = 255, message = "Push description must be less than or equal to 255 characters.")
    @Schema(description = "Description of the push notification", example = "Check out our new features available now!")
    private String description;

    @Column(name = "created_at", nullable = false)
    @Schema(description = "Date when the push notification was created", example = "2024-11-05")
    private LocalDate createdAt;

    @ManyToMany
    @JoinTable(
            name = "users_push",
            joinColumns = @JoinColumn(name = "id_push"),
            inverseJoinColumns = @JoinColumn(name = "id_user")
    )
    @Schema(description = "Users who will receive this push notification")
    private Set<User> users = new HashSet<>();

}
