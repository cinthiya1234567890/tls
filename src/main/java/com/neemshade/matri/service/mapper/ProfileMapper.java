package com.neemshade.matri.service.mapper;


import com.neemshade.matri.domain.*;
import com.neemshade.matri.service.dto.ProfileDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Profile} and its DTO {@link ProfileDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ProfileMapper extends EntityMapper<ProfileDTO, Profile> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    ProfileDTO toDto(Profile profile);

    @Mapping(source = "userId", target = "user")
    @Mapping(target = "profileParams", ignore = true)
    @Mapping(target = "removeProfileParam", ignore = true)
    @Mapping(target = "photos", ignore = true)
    @Mapping(target = "removePhoto", ignore = true)
    @Mapping(target = "sents", ignore = true)
    @Mapping(target = "removeSent", ignore = true)
    @Mapping(target = "receiveds", ignore = true)
    @Mapping(target = "removeReceived", ignore = true)
    @Mapping(target = "queries", ignore = true)
    @Mapping(target = "removeQuery", ignore = true)
    @Mapping(target = "payments", ignore = true)
    @Mapping(target = "removePayment", ignore = true)
    @Mapping(target = "issueds", ignore = true)
    @Mapping(target = "removeIssued", ignore = true)
    @Mapping(target = "addresseds", ignore = true)
    @Mapping(target = "removeAddressed", ignore = true)
    Profile toEntity(ProfileDTO profileDTO);

    default Profile fromId(Long id) {
        if (id == null) {
            return null;
        }
        Profile profile = new Profile();
        profile.setId(id);
        return profile;
    }
}
