package com.neemshade.matri.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;

/**
 * A DTO for the {@link com.neemshade.matri.domain.Field} entity.
 */
@ApiModel(description = "all possible fields that profile record can have")
public class FieldDTO implements Serializable {
    
    private Long id;

    private String fieldName;

    private Integer peckOrder;


    private Long dataSourceId;

    private String dataSourceParamTitle;

    private Long dataTypeId;

    private String dataTypeParamTitle;

    private Long cascaderId;

    private String cascaderCascaderName;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Integer getPeckOrder() {
        return peckOrder;
    }

    public void setPeckOrder(Integer peckOrder) {
        this.peckOrder = peckOrder;
    }

    public Long getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(Long cascaderParamId) {
        this.dataSourceId = cascaderParamId;
    }

    public String getDataSourceParamTitle() {
        return dataSourceParamTitle;
    }

    public void setDataSourceParamTitle(String cascaderParamParamTitle) {
        this.dataSourceParamTitle = cascaderParamParamTitle;
    }

    public Long getDataTypeId() {
        return dataTypeId;
    }

    public void setDataTypeId(Long cascaderParamId) {
        this.dataTypeId = cascaderParamId;
    }

    public String getDataTypeParamTitle() {
        return dataTypeParamTitle;
    }

    public void setDataTypeParamTitle(String cascaderParamParamTitle) {
        this.dataTypeParamTitle = cascaderParamParamTitle;
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
        if (!(o instanceof FieldDTO)) {
            return false;
        }

        return id != null && id.equals(((FieldDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FieldDTO{" +
            "id=" + getId() +
            ", fieldName='" + getFieldName() + "'" +
            ", peckOrder=" + getPeckOrder() +
            ", dataSourceId=" + getDataSourceId() +
            ", dataSourceParamTitle='" + getDataSourceParamTitle() + "'" +
            ", dataTypeId=" + getDataTypeId() +
            ", dataTypeParamTitle='" + getDataTypeParamTitle() + "'" +
            ", cascaderId=" + getCascaderId() +
            ", cascaderCascaderName='" + getCascaderCascaderName() + "'" +
            "}";
    }
}
