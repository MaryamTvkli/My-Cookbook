package com.abn.assessment.mycookbook.domain;

import com.abn.assessment.mycookbook.dto.model.UnitOfMeasureDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UnitOfMeasure implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    public UnitOfMeasureDTO convertEntityToDTO(){
        return new ModelMapper().map(this, UnitOfMeasureDTO.class);
    }
}
