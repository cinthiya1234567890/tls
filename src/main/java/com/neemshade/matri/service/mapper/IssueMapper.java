package com.neemshade.matri.service.mapper;


import com.neemshade.matri.domain.*;
import com.neemshade.matri.service.dto.IssueDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Issue} and its DTO {@link IssueDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProfileMapper.class})
public interface IssueMapper extends EntityMapper<IssueDTO, Issue> {

    @Mapping(source = "complaint.id", target = "complaintId")
    @Mapping(source = "admin.id", target = "adminId")
    IssueDTO toDto(Issue issue);

    @Mapping(source = "complaintId", target = "complaint")
    @Mapping(source = "adminId", target = "admin")
    Issue toEntity(IssueDTO issueDTO);

    default Issue fromId(Long id) {
        if (id == null) {
            return null;
        }
        Issue issue = new Issue();
        issue.setId(id);
        return issue;
    }
}
