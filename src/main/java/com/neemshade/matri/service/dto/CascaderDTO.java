package com.neemshade.matri.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;

/**
 * A DTO for the {@link com.neemshade.matri.domain.Cascader} entity.
 */
@ApiModel(description = "possible values that a profileData can have\ncascader can be recursive, like location - country - state -city\nthis set of data are stored in Cascader and CascaderParam tables.\n\ncanEnterCustomValue decides if user can place custom value instead of dropdown menu")
public class CascaderDTO implements Serializable {
    
    private Long id;

    private String cascaderName;

    private Boolean canEnterCustomValue;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCascaderName() {
        return cascaderName;
    }

    public void setCascaderName(String cascaderName) {
        this.cascaderName = cascaderName;
    }

    public Boolean isCanEnterCustomValue() {
        return canEnterCustomValue;
    }

    public void setCanEnterCustomValue(Boolean canEnterCustomValue) {
        this.canEnterCustomValue = canEnterCustomValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CascaderDTO)) {
            return false;
        }

        return id != null && id.equals(((CascaderDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CascaderDTO{" +
            "id=" + getId() +
            ", cascaderName='" + getCascaderName() + "'" +
            ", canEnterCustomValue='" + isCanEnterCustomValue() + "'" +
            "}";
    }
}
