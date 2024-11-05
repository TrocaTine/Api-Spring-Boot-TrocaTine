package com.example.apitrocatinesql.models;

import io.swagger.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Schema(description = "Category information with details like name and associated products.")
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Category ID", example = "1")
    @Column(name = "id_category")
    private Integer idCategory;

    @Schema(description = "Category name", example = "Clothing")
    @Column(name = "name", nullable = false)
    @Size(min = 2, message = "Category name must have a value greater than 2 characters")
    private String name;

    @ManyToMany(mappedBy = "categories")
    @Schema(description = "Set of products associated with this category")
    private Set<Product> products;
}
