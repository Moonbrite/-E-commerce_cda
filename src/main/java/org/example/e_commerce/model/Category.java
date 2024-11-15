package org.example.e_commerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true) // Nom unique pour une catégorie
    private String name;

    // Relation Parent-Enfant pour gérer la hiérarchie
    @ManyToOne
    @JoinColumn(name = "parent_id") // Champ pour le parent
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference // Enfants de la catégorie
    private List<Category> subCategories = new ArrayList<>();
}
