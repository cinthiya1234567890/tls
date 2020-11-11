package com.neemshade.matri.service.mapper;


import com.neemshade.matri.domain.*;
import com.neemshade.matri.service.dto.QueryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Query} and its DTO {@link QueryDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProfileMapper.class})
public interface QueryMapper extends EntityMapper<QueryDTO, Query> {

    @Mapping(source = "profile.id", target = "profileId")
    @Mapping(source = "profile.name", target = "profileName")
    QueryDTO toDto(Query query);

    @Mapping(target = "queryPlates", ignore = true)
    @Mapping(target = "removeQueryPlate", ignore = true)
    @Mapping(source = "profileId", target = "profile")
    Query toEntity(QueryDTO queryDTO);

    default Query fromId(Long id) {
        if (id == null) {
            return null;
        }
        Query query = new Query();
        query.setId(id);
        return query;
    }
}
