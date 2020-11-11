package com.neemshade.matri.service.mapper;


import com.neemshade.matri.domain.*;
import com.neemshade.matri.service.dto.PlateParamDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PlateParam} and its DTO {@link PlateParamDTO}.
 */
@Mapper(componentModel = "spring", uses = {QueryPlateMapper.class, CascaderMapper.class})
public interface PlateParamMapper extends EntityMapper<PlateParamDTO, PlateParam> {

    @Mapping(source = "queryPlate.id", target = "queryPlateId")
    @Mapping(source = "cascader.id", target = "cascaderId")
    @Mapping(source = "cascader.cascaderName", target = "cascaderCascaderName")
    PlateParamDTO toDto(PlateParam plateParam);

    @Mapping(source = "queryPlateId", target = "queryPlate")
    @Mapping(source = "cascaderId", target = "cascader")
    PlateParam toEntity(PlateParamDTO plateParamDTO);

    default PlateParam fromId(Long id) {
        if (id == null) {
            return null;
        }
        PlateParam plateParam = new PlateParam();
        plateParam.setId(id);
        return plateParam;
    }
}
