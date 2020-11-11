package com.neemshade.matri.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;

/**
 * A DTO for the {@link com.neemshade.matri.domain.PlateParam} entity.
 */
@ApiModel(description = "the query values that are checked for a QueryPlate")
public class PlateParamDTO implements Serializable {
    
    private Long id;

    private String value;

    private Integer peckOrder;


    private Long queryPlateId;

    private Long cascaderId;

    private String cascaderCascaderName;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getPeckOrder() {
        return peckOrder;
    }

    public void setPeckOrder(Integer peckOrder) {
        this.peckOrder = peckOrder;
    }

    public Long getQueryPlateId() {
        return queryPlateId;
    }

    public void setQueryPlateId(Long queryPlateId) {
        this.queryPlateId = queryPlateId;
    }

    public Long getCascaderId() {
        return cascaderId;
    }

    public void setCascaderId(Long cascaderId) {
        this.cascaderId = cascaderId;
    }

    public String getCascaderCascaderName() {
        return cascaderCascaderName;
    }

    public void setCascaderCascaderName(String cascaderCascaderName) {
        this.cascaderCascaderName = cascaderCascaderName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PlateParamDTO)) {
            return false;
        }

        return id != null && id.equals(((PlateParamDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PlateParamDTO{" +
            "id=" + getId() +
            ", value='" + getValue() + "'" +
            ", peckOrder=" + getPeckOrder() +
            ", queryPlateId=" + getQueryPlateId() +
            ", cascaderId=" + getCascaderId() +
            ", cascaderCascaderName='" + getCascaderCascaderName() + "'" +
            "}";
    }
}
