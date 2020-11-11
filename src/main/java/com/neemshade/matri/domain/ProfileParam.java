package com.neemshade.matri.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * actual data belonging to a person\n\nuserEnteredCustomValue comes into picture when datatype is cascader,\nwhich has canEnterCustomValue=true. In that case, user may enter\na custom value for this param.  That value is stored in userEnteredCustomValue\n
 */
@Entity
@Table(name = "profile_param")
public class ProfileParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_value")
    private String dataValue;

    @Column(name = "data_int")
    private Integer dataInt;

    @Column(name = "user_entered_custom_value")
    private String userEnteredCustomValue;

    @ManyToOne
    @JsonIgnoreProperties(value = "profileParams", allowSetters = true)
    private Profile profile;

    @ManyToOne
    @JsonIgnoreProperties(value = "profileParams", allowSetters = true)
    private Field field;

    @ManyToOne
    @JsonIgnoreProperties(value = "profileParams", allowSetters = true)
    private CascaderParam cascaderParam;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataValue() {
        return dataValue;
    }

    public ProfileParam dataValue(String dataValue) {
        this.dataValue = dataValue;
        return this;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }

    public Integer getDataInt() {
        return dataInt;
    }

    public ProfileParam dataInt(Integer dataInt) {
        this.dataInt = dataInt;
        return this;
    }

    public void setDataInt(Integer dataInt) {
        this.dataInt = dataInt;
    }

    public String getUserEnteredCustomValue() {
        return userEnteredCustomValue;
    }

    public ProfileParam userEnteredCustomValue(String userEnteredCustomValue) {
        this.userEnteredCustomValue = userEnteredCustomValue;
        return this;
    }

    public void setUserEnteredCustomValue(String userEnteredCustomValue) {
        this.userEnteredCustomValue = userEnteredCustomValue;
    }

    public Profile getProfile() {
        return profile;
    }

    public ProfileParam profile(Profile profile) {
        this.profile = profile;
        return this;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Field getField() {
        return field;
    }

    public ProfileParam field(Field field) {
        this.field = field;
        return this;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public CascaderParam getCascaderParam() {
        return cascaderParam;
    }

    public ProfileParam cascaderParam(CascaderParam cascaderParam) {
        this.cascaderParam = cascaderParam;
        return this;
    }

    public void setCascaderParam(CascaderParam cascaderParam) {
        this.cascaderParam = cascaderParam;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProfileParam)) {
            return false;
        }
        return id != null && id.equals(((ProfileParam) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProfileParam{" +
            "id=" + getId() +
            ", dataValue='" + getDataValue() + "'" +
            ", dataInt=" + getDataInt() +
            ", userEnteredCustomValue='" + getUserEnteredCustomValue() + "'" +
            "}";
    }
}
