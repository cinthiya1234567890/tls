package com.neemshade.matri.service.dto;

import io.swagger.annotations.ApiModel;
import java.time.Instant;
import java.io.Serializable;

/**
 * A DTO for the {@link com.neemshade.matri.domain.Issue} entity.
 */
@ApiModel(description = "issue raised by the user and the status of the same")
public class IssueDTO implements Serializable {
    
    private Long id;

    private Instant createdTime;

    private Instant updatedTime;

    private String description;


    private Long complaintId;

    private Long adminId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Instant createdTime) {
        this.createdTime = createdTime;
    }

    public Instant getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Instant updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Long profileId) {
        this.complaintId = profileId;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long profileId) {
        this.adminId = profileId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IssueDTO)) {
            return false;
        }

        return id != null && id.equals(((IssueDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "IssueDTO{" +
            "id=" + getId() +
            ", createdTime='" + getCreatedTime() + "'" +
            ", updatedTime='" + getUpdatedTime() + "'" +
            ", description='" + getDescription() + "'" +
            ", complaintId=" + getComplaintId() +
            ", adminId=" + getAdminId() +
            "}";
    }
}
