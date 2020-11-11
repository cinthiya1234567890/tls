package com.neemshade.matri.service.mapper;


import com.neemshade.matri.domain.*;
import com.neemshade.matri.service.dto.FieldDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Field} and its DTO {@link FieldDTO}.
 */
@Mapper(componentModel = "spring", uses = {CascaderParamMapper.class, CascaderMapper.class})
public interface FieldMapper extends EntityMapper<FieldDTO, Field> {

    @Mapping(source = "dataSource.id", target = "dataSourceId")
    @Mapping(source = "dataSource.paramTitle", target = "dataSourceParamTitle")
    @Mapping(source = "dataType.id", target = "dataTypeId")
    @Mapping(source = "dataType.paramTitle", target = "dataTypeParamTitle")
    @Mapping(source = "cascader.id", target = "cascaderId")
    @Mapping(source = "cascader.cascaderName", target = "cascaderCascaderName")
    FieldDTO toDto(Field field);

    @Mapping(target = "fieldAttributes", ignore = true)
    @Mapping(target = "removeFieldAttribute", ignore = true)
    @Mapping(target = "profileParams", ignore = true)
    @Mapping(target = "removeProfileParam", ignore = true)
    @Mapping(target = "queryPlates", ignore = true)
    @Mapping(target = "removeQueryPlate", ignore = true)
    @Mapping(target = "malaParams", ignore = true)
    @Mapping(target = "removeMalaParam", ignore = true)
    @Mapping(source = "dataSourceId", target = "dataSource")
    @Mapping(source = "dataTypeId", target = "dataType")
    @Mapping(source = "cascaderId", target = "cascader")
    Field toEntity(FieldDTO fieldDTO);

    default Field fromId(Long id) {
        if (id == null) {
            return null;
        }
        Field field = new Field();
        field.setId(id);
        return field;
    }
}
