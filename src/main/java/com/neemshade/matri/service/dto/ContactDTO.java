package com.neemshade.matri.service.dto;

import io.swagger.annotations.ApiModel;
import java.time.Instant;
import java.io.Serializable;

/**
 * A DTO for the {@link com.neemshade.matri.domain.Contact} entity.
 */
@ApiModel(description = "when a profile pass interest, decline, match are stored in Contact table")
public class ContactDTO implements Serializable {
    
    private Long id;

    private Instant initiatedDate;

    private Instant updatedDate;


    private Long contactStatusId;

    private String contactStatusParamTitle;

    private Long senderId;

    private Long receiverId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getInitiatedDate() {
        return initiatedDate;
    }

    public void setInitiatedDate(Instant initiatedDate) {
        this.initiatedDate = initiatedDate;
    }

    public Instant getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Instant updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getContactStatusId() {
        return contactStatusId;
    }

    public void setContactStatusId(Long cascaderParamId) {
        this.contactStatusId = cascaderParamId;
    }

    public String getContactStatusParamTitle() {
        return contactStatusParamTitle;
    }

    public void setContactStatusParamTitle(String cascaderParamParamTitle) {
        this.contactStatusParamTitle = cascaderParamParamTitle;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long profileId) {
        this.senderId = profileId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long profileId) {
        this.receiverId = profileId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContactDTO)) {
            return false;
        }

        return id != null && id.equals(((ContactDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ContactDTO{" +
            "id=" + getId() +
            ", initiatedDate='" + getInitiatedDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", contactStatusId=" + getContactStatusId() +
            ", contactStatusParamTitle='" + getContactStatusParamTitle() + "'" +
            ", senderId=" + getSenderId() +
            ", receiverId=" + getReceiverId() +
            "}";
    }
}
