package com.neemshade.matri.service.mapper;


import com.neemshade.matri.domain.*;
import com.neemshade.matri.service.dto.CascaderParamDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CascaderParam} and its DTO {@link CascaderParamDTO}.
 */
@Mapper(componentModel = "spring", uses = {CascaderMapper.class})
public interface CascaderParamMapper extends EntityMapper<CascaderParamDTO, CascaderParam> {

    @Mapping(source = "parent.id", target = "parentId")
    @Mapping(source = "cascader.id", target = "cascaderId")
    @Mapping(source = "cascader.cascaderName", target = "cascaderCascaderName")
    CascaderParamDTO toDto(CascaderParam cascaderParam);

    @Mapping(target = "dataSourcers", ignore = true)
    @Mapping(target = "removeDataSourcer", ignore = true)
    @Mapping(target = "dataTypers", ignore = true)
    @Mapping(target = "removeDataTyper", ignore = true)
    @Mapping(target = "profileParams", ignore = true)
    @Mapping(target = "removeProfileParam", ignore = true)
    @Mapping(target = "contacts", ignore = true)
    @Mapping(target = "removeContact", ignore = true)
    @Mapping(target = "children", ignore = true)
    @Mapping(target = "removeChild", ignore = true)
    @Mapping(source = "parentId", target = "parent")
    @Mapping(source = "cascaderId", target = "cascader")
    CascaderParam toEntity(CascaderParamDTO cascaderParamDTO);

    default CascaderParam fromId(Long id) {
        if (id == null) {
            return null;
        }
        CascaderParam cascaderParam = new CascaderParam();
        cascaderParam.setId(id);
        return cascaderParam;
    }
}
