package pl.jarugalucas.spring5recipeapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // without setting up cascade. Recipe owns notes. I don't want to for example delete recipe while deleting note
    @OneToOne
    private Recipe recipe;

    // Specifies that a persistent property or field should be persisted as a large object to a database-supported large object type
    @Lob
    private String recipeNotes;
}