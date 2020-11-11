package com.neemshade.matri.service.mapper;


import com.neemshade.matri.domain.*;
import com.neemshade.matri.service.dto.MalaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Mala} and its DTO {@link MalaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MalaMapper extends EntityMapper<MalaDTO, Mala> {


    @Mapping(target = "malaParams", ignore = true)
    @Mapping(target = "removeMalaParam", ignore = true)
    Mala toEntity(MalaDTO malaDTO);

    default Mala fromId(Long id) {
        if (id == null) {
            return null;
        }
        Mala mala = new Mala();
        mala.setId(id);
        return mala;
    }
}
