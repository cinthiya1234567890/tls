package com.neemshade.matri.service.mapper;


import com.neemshade.matri.domain.*;
import com.neemshade.matri.service.dto.FieldAttributeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FieldAttribute} and its DTO {@link FieldAttributeDTO}.
 */
@Mapper(componentModel = "spring", uses = {FieldMapper.class})
public interface FieldAttributeMapper extends EntityMapper<FieldAttributeDTO, FieldAttribute> {

    @Mapping(source = "field.id", target = "fieldId")
    @Mapping(source = "field.fieldName", target = "fieldFieldName")
    FieldAttributeDTO toDto(FieldAttribute fieldAttribute);

    @Mapping(source = "fieldId", target = "field")
    FieldAttribute toEntity(FieldAttributeDTO fieldAttributeDTO);

    default FieldAttribute fromId(Long id) {
        if (id == null) {
            return null;
        }
        FieldAttribute fieldAttribute = new FieldAttribute();
        fieldAttribute.setId(id);
        return fieldAttribute;
    }
}
