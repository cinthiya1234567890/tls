package com.neemshade.matri.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;

/**
 * A DTO for the {@link com.neemshade.matri.domain.MalaParam} entity.
 */
@ApiModel(description = "the list of fields of a Mala")
public class MalaParamDTO implements Serializable {
    
    private Long id;

    private Integer peckOrder;


    private Long malaId;

    private String malaMalaName;

    private Long fieldId;

    private String fieldFieldName;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPeckOrder() {
        return peckOrder;
    }

    public void setPeckOrder(Integer peckOrder) {
        this.peckOrder = peckOrder;
    }

    public Long getMalaId() {
        return malaId;
    }

    public void setMalaId(Long malaId) {
        this.malaId = malaId;
    }

    public String getMalaMalaName() {
        return malaMalaName;
    }

    public void setMalaMalaName(String malaMalaName) {
        this.malaMalaName = malaMalaName;
    }

    public Long getFieldId() {
        return fieldId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }

    public String getFieldFieldName() {
        return fieldFieldName;
    }

    public void setFieldFieldName(String fieldFieldName) {
        this.fieldFieldName = fieldFieldName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MalaParamDTO)) {
            return false;
        }

        return id != null && id.equals(((MalaParamDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MalaParamDTO{" +
            "id=" + getId() +
            ", peckOrder=" + getPeckOrder() +
            ", malaId=" + getMalaId() +
            ", malaMalaName='" + getMalaMalaName() + "'" +
            ", fieldId=" + getFieldId() +
            ", fieldFieldName='" + getFieldFieldName() + "'" +
            "}";
    }
}
