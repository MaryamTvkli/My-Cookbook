package com.abn.assessment.mycookbook.domain;

import com.abn.assessment.mycookbook.dto.model.IngredientDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
@AllArgsConstructor
public class Ingredient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private BigDecimal amount;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure uom;

    @ManyToOne
    @JsonIgnore
    private Recipe recipe;

    public Ingredient() {
    }

    public Ingredient(String name, BigDecimal amount, UnitOfMeasure uom, Recipe recipe) {
        this.name = name;
        this.amount = amount;
        this.uom = uom;
        this.recipe = recipe;
    }
    public IngredientDTO convertEntityToDTO(){
        return new ModelMapper().map(this, IngredientDTO.class);
    }
}
