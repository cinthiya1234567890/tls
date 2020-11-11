package com.neemshade.matri.service.mapper;


import com.neemshade.matri.domain.*;
import com.neemshade.matri.service.dto.MalaParamDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MalaParam} and its DTO {@link MalaParamDTO}.
 */
@Mapper(componentModel = "spring", uses = {MalaMapper.class, FieldMapper.class})
public interface MalaParamMapper extends EntityMapper<MalaParamDTO, MalaParam> {

    @Mapping(source = "mala.id", target = "malaId")
    @Mapping(source = "mala.malaName", target = "malaMalaName")
    @Mapping(source = "field.id", target = "fieldId")
    @Mapping(source = "field.fieldName", target = "fieldFieldName")
    MalaParamDTO toDto(MalaParam malaParam);

    @Mapping(source = "malaId", target = "mala")
    @Mapping(source = "fieldId", target = "field")
    MalaParam toEntity(MalaParamDTO malaParamDTO);

    default MalaParam fromId(Long id) {
        if (id == null) {
            return null;
        }
        MalaParam malaParam = new MalaParam();
        malaParam.setId(id);
        return malaParam;
    }
}
