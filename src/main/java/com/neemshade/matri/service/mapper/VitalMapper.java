package com.neemshade.matri.service.mapper;


import com.neemshade.matri.domain.*;
import com.neemshade.matri.service.dto.VitalDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Vital} and its DTO {@link VitalDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface VitalMapper extends EntityMapper<VitalDTO, Vital> {



    default Vital fromId(Long id) {
        if (id == null) {
            return null;
        }
        Vital vital = new Vital();
        vital.setId(id);
        return vital;
    }
}
