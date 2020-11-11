package com.neemshade.matri.service.mapper;


import com.neemshade.matri.domain.*;
import com.neemshade.matri.service.dto.ContactDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Contact} and its DTO {@link ContactDTO}.
 */
@Mapper(componentModel = "spring", uses = {CascaderParamMapper.class, ProfileMapper.class})
public interface ContactMapper extends EntityMapper<ContactDTO, Contact> {

    @Mapping(source = "contactStatus.id", target = "contactStatusId")
    @Mapping(source = "contactStatus.paramTitle", target = "contactStatusParamTitle")
    @Mapping(source = "sender.id", target = "senderId")
    @Mapping(source = "receiver.id", target = "receiverId")
    ContactDTO toDto(Contact contact);

    @Mapping(source = "contactStatusId", target = "contactStatus")
    @Mapping(source = "senderId", target = "sender")
    @Mapping(source = "receiverId", target = "receiver")
    Contact toEntity(ContactDTO contactDTO);

    default Contact fromId(Long id) {
        if (id == null) {
            return null;
        }
        Contact contact = new Contact();
        contact.setId(id);
        return contact;
    }
}
