package com.neemshade.matri.service.mapper;


import com.neemshade.matri.domain.*;
import com.neemshade.matri.service.dto.CascaderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Cascader} and its DTO {@link CascaderDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CascaderMapper extends EntityMapper<CascaderDTO, Cascader> {


    @Mapping(target = "fields", ignore = true)
    @Mapping(target = "removeField", ignore = true)
    @Mapping(target = "cascaderParams", ignore = true)
    @Mapping(target = "removeCascaderParam", ignore = true)
    @Mapping(target = "plateParams", ignore = true)
    @Mapping(target = "removePlateParam", ignore = true)
    Cascader toEntity(CascaderDTO cascaderDTO);

    default Cascader fromId(Long id) {
        if (id == null) {
            return null;
        }
        Cascader cascader = new Cascader();
        cascader.setId(id);
        return cascader;
    }
}
