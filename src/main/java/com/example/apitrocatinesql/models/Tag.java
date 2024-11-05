package com.example.apitrocatinesql.models;

import io.swagger.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Schema(description = "Represents a tag that can be associated with products.")
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tag")
    @Schema(description = "Unique identifier for the tag", example = "1")
    private Long idTag;

    @Column(name = "type", nullable = false)
    @NotBlank(message = "Type is required.")
    @Schema(description = "Type of the tag", example = "Category")
    private String type;

    @Column(name = "name")
    @Size(max = 100, message = "Name cannot exceed 100 characters.")
    @Schema(description = "Name of the tag", example = "Electronics")
    private String name;

    @ManyToMany(mappedBy = "tags")
    @Schema(description = "Products associated with this tag")
    private Set<Product> products;

}
