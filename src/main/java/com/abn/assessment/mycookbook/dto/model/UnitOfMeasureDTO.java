package com.abn.assessment.mycookbook.dto.model;

import com.abn.assessment.mycookbook.domain.UnitOfMeasure;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class UnitOfMeasureDTO {
    private String description;
    public UnitOfMeasure convertDTOToEntity(){
        return new ModelMapper().map(this,UnitOfMeasure.class);
    }
}
