package com.neemshade.matri.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;

/**
 * A DTO for the {@link com.neemshade.matri.domain.Mala} entity.
 */
@ApiModel(description = "collection of parameters (fields)")
public class MalaDTO implements Serializable {
    
    private Long id;

    private String malaName;

    private Boolean isEditable;

    private String description;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMalaName() {
        return malaName;
    }

    public void setMalaName(String malaName) {
        this.malaName = malaName;
    }

    public Boolean isIsEditable() {
        return isEditable;
    }

    public void setIsEditable(Boolean isEditable) {
        this.isEditable = isEditable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MalaDTO)) {
            return false;
        }

        return id != null && id.equals(((MalaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MalaDTO{" +
            "id=" + getId() +
            ", malaName='" + getMalaName() + "'" +
            ", isEditable='" + isIsEditable() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
