package com.neemshade.matri.service.mapper;


import com.neemshade.matri.domain.*;
import com.neemshade.matri.service.dto.QueryPlateDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link QueryPlate} and its DTO {@link QueryPlateDTO}.
 */
@Mapper(componentModel = "spring", uses = {QueryMapper.class, FieldMapper.class})
public interface QueryPlateMapper extends EntityMapper<QueryPlateDTO, QueryPlate> {

    @Mapping(source = "query.id", target = "queryId")
    @Mapping(source = "query.queryName", target = "queryQueryName")
    @Mapping(source = "field.id", target = "fieldId")
    @Mapping(source = "field.fieldName", target = "fieldFieldName")
    QueryPlateDTO toDto(QueryPlate queryPlate);

    @Mapping(target = "plateParams", ignore = true)
    @Mapping(target = "removePlateParam", ignore = true)
    @Mapping(source = "queryId", target = "query")
    @Mapping(source = "fieldId", target = "field")
    QueryPlate toEntity(QueryPlateDTO queryPlateDTO);

    default QueryPlate fromId(Long id) {
        if (id == null) {
            return null;
        }
        QueryPlate queryPlate = new QueryPlate();
        queryPlate.setId(id);
        return queryPlate;
    }
}
