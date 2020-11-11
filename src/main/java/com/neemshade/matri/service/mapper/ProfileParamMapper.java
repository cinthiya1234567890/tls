package com.neemshade.matri.service.mapper;


import com.neemshade.matri.domain.*;
import com.neemshade.matri.service.dto.ProfileParamDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProfileParam} and its DTO {@link ProfileParamDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProfileMapper.class, FieldMapper.class, CascaderParamMapper.class})
public interface ProfileParamMapper extends EntityMapper<ProfileParamDTO, ProfileParam> {

    @Mapping(source = "profile.id", target = "profileId")
    @Mapping(source = "profile.name", target = "profileName")
    @Mapping(source = "field.id", target = "fieldId")
    @Mapping(source = "field.fieldName", target = "fieldFieldName")
    @Mapping(source = "cascaderParam.id", target = "cascaderParamId")
    @Mapping(source = "cascaderParam.paramTitle", target = "cascaderParamParamTitle")
    ProfileParamDTO toDto(ProfileParam profileParam);

    @Mapping(source = "profileId", target = "profile")
    @Mapping(source = "fieldId", target = "field")
    @Mapping(source = "cascaderParamId", target = "cascaderParam")
    ProfileParam toEntity(ProfileParamDTO profileParamDTO);

    default ProfileParam fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProfileParam profileParam = new ProfileParam();
        profileParam.setId(id);
        return profileParam;
    }
}
