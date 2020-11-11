package com.neemshade.matri.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;

/**
 * A DTO for the {@link com.neemshade.matri.domain.ProfileParam} entity.
 */
@ApiModel(description = "actual data belonging to a person\n\nuserEnteredCustomValue comes into picture when datatype is cascader,\nwhich has canEnterCustomValue=true. In that case, user may enter\na custom value for this param.  That value is stored in userEnteredCustomValue\n")
public class ProfileParamDTO implements Serializable {
    
    private Long id;

    private String dataValue;

    private Integer dataInt;

    private String userEnteredCustomValue;


    private Long profileId;

    private String profileName;

    private Long fieldId;

    private String fieldFieldName;

    private Long cascaderParamId;

    private String cascaderParamParamTitle;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }

    public Integer getDataInt() {
        return dataInt;
    }

    public void setDataInt(Integer dataInt) {
        this.dataInt = dataInt;
    }

    public String getUserEnteredCustomValue() {
        return userEnteredCustomValue;
    }

    public void setUserEnteredCustomValue(String userEnteredCustomValue) {
        this.userEnteredCustomValue = userEnteredCustomValue;
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

    public Long getCascaderParamId() {
        return cascaderParamId;
    }

    public void setCascaderParamId(Long cascaderParamId) {
        this.cascaderParamId = cascaderParamId;
    }

    public String getCascaderParamParamTitle() {
        return cascaderParamParamTitle;
    }

    public void setCascaderParamParamTitle(String cascaderParamParamTitle) {
        this.cascaderParamParamTitle = cascaderParamParamTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProfileParamDTO)) {
            return false;
        }

        return id != null && id.equals(((ProfileParamDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProfileParamDTO{" +
            "id=" + getId() +
            ", dataValue='" + getDataValue() + "'" +
            ", dataInt=" + getDataInt() +
            ", userEnteredCustomValue='" + getUserEnteredCustomValue() + "'" +
            ", profileId=" + getProfileId() +
            ", profileName='" + getProfileName() + "'" +
            ", fieldId=" + getFieldId() +
            ", fieldFieldName='" + getFieldFieldName() + "'" +
            ", cascaderParamId=" + getCascaderParamId() +
            ", cascaderParamParamTitle='" + getCascaderParamParamTitle() + "'" +
            "}";
    }
}
