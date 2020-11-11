package com.neemshade.matri.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;

/**
 * A DTO for the {@link com.neemshade.matri.domain.Query} entity.
 */
@ApiModel(description = "a collection of queries placed together\nfor eg, \"profiles in my district\" and \"mother tongue tamil, telugu\"")
public class QueryDTO implements Serializable {
    
    private Long id;

    private String queryName;

    private Integer peckOrder;


    private Long profileId;

    private String profileName;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public Integer getPeckOrder() {
        return peckOrder;
    }

    public void setPeckOrder(Integer peckOrder) {
        this.peckOrder = peckOrder;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QueryDTO)) {
            return false;
        }

        return id != null && id.equals(((QueryDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QueryDTO{" +
            "id=" + getId() +
            ", queryName='" + getQueryName() + "'" +
            ", peckOrder=" + getPeckOrder() +
            ", profileId=" + getProfileId() +
            ", profileName='" + getProfileName() + "'" +
            "}";
    }
}
