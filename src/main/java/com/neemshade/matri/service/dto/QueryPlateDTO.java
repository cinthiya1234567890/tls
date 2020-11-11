package com.neemshade.matri.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;

/**
 * A DTO for the {@link com.neemshade.matri.domain.QueryPlate} entity.
 */
@ApiModel(description = "single query")
public class QueryPlateDTO implements Serializable {
    
    private Long id;

    private Boolean isRange;

    private Boolean isMulti;

    private Integer peckOrder;


    private Long queryId;

    private String queryQueryName;

    private Long fieldId;

    private String fieldFieldName;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isIsRange() {
        return isRange;
    }

    public void setIsRange(Boolean isRange) {
        this.isRange = isRange;
    }

    public Boolean isIsMulti() {
        return isMulti;
    }

    public void setIsMulti(Boolean isMulti) {
        this.isMulti = isMulti;
    }

    public Integer getPeckOrder() {
        return peckOrder;
    }

    public void setPeckOrder(Integer peckOrder) {
        this.peckOrder = peckOrder;
    }

    public Long getQueryId() {
        return queryId;
    }

    public void setQueryId(Long queryId) {
        this.queryId = queryId;
    }

    public String getQueryQueryName() {
        return queryQueryName;
    }

    public void setQueryQueryName(String queryQueryName) {
        this.queryQueryName = queryQueryName;
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
        if (!(o instanceof QueryPlateDTO)) {
            return false;
        }

        return id != null && id.equals(((QueryPlateDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QueryPlateDTO{" +
            "id=" + getId() +
            ", isRange='" + isIsRange() + "'" +
            ", isMulti='" + isIsMulti() + "'" +
            ", peckOrder=" + getPeckOrder() +
            ", queryId=" + getQueryId() +
            ", queryQueryName='" + getQueryQueryName() + "'" +
            ", fieldId=" + getFieldId() +
            ", fieldFieldName='" + getFieldFieldName() + "'" +
            "}";
    }
}
