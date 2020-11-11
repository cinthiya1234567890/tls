package com.neemshade.matri.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.neemshade.matri.domain.FieldAttribute} entity.
 */
public class FieldAttributeDTO implements Serializable {
    
    private Long id;

    private String attributeName;

    private String attributeValue;


    private Long fieldId;

    private String fieldFieldName;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
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
        if (!(o instanceof FieldAttributeDTO)) {
            return false;
        }

        return id != null && id.equals(((FieldAttributeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FieldAttributeDTO{" +
            "id=" + getId() +
            ", attributeName='" + getAttributeName() + "'" +
            ", attributeValue='" + getAttributeValue() + "'" +
            ", fieldId=" + getFieldId() +
            ", fieldFieldName='" + getFieldFieldName() + "'" +
            "}";
    }
}
