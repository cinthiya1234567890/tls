package com.neemshade.matri.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * all possible fields that profile record can have
 */
@Entity
@Table(name = "field")
public class Field implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "field_name")
    private String fieldName;

    @Column(name = "peck_order")
    private Integer peckOrder;

    @OneToMany(mappedBy = "field")
    private Set<FieldAttribute> fieldAttributes = new HashSet<>();

    @OneToMany(mappedBy = "field")
    private Set<ProfileParam> profileParams = new HashSet<>();

    @OneToMany(mappedBy = "field")
    private Set<QueryPlate> queryPlates = new HashSet<>();

    @OneToMany(mappedBy = "field")
    private Set<MalaParam> malaParams = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "dataSourcers", allowSetters = true)
    private CascaderParam dataSource;

    @ManyToOne
    @JsonIgnoreProperties(value = "dataTypers", allowSetters = true)
    private CascaderParam dataType;

    @ManyToOne
    @JsonIgnoreProperties(value = "fields", allowSetters = true)
    private Cascader cascader;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Field fieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Integer getPeckOrder() {
        return peckOrder;
    }

    public Field peckOrder(Integer peckOrder) {
        this.peckOrder = peckOrder;
        return this;
    }

    public void setPeckOrder(Integer peckOrder) {
        this.peckOrder = peckOrder;
    }

    public Set<FieldAttribute> getFieldAttributes() {
        return fieldAttributes;
    }

    public Field fieldAttributes(Set<FieldAttribute> fieldAttributes) {
        this.fieldAttributes = fieldAttributes;
        return this;
    }

    public Field addFieldAttribute(FieldAttribute fieldAttribute) {
        this.fieldAttributes.add(fieldAttribute);
        fieldAttribute.setField(this);
        return this;
    }

    public Field removeFieldAttribute(FieldAttribute fieldAttribute) {
        this.fieldAttributes.remove(fieldAttribute);
        fieldAttribute.setField(null);
        return this;
    }

    public void setFieldAttributes(Set<FieldAttribute> fieldAttributes) {
        this.fieldAttributes = fieldAttributes;
    }

    public Set<ProfileParam> getProfileParams() {
        return profileParams;
    }

    public Field profileParams(Set<ProfileParam> profileParams) {
        this.profileParams = profileParams;
        return this;
    }

    public Field addProfileParam(ProfileParam profileParam) {
        this.profileParams.add(profileParam);
        profileParam.setField(this);
        return this;
    }

    public Field removeProfileParam(ProfileParam profileParam) {
        this.profileParams.remove(profileParam);
        profileParam.setField(null);
        return this;
    }

    public void setProfileParams(Set<ProfileParam> profileParams) {
        this.profileParams = profileParams;
    }

    public Set<QueryPlate> getQueryPlates() {
        return queryPlates;
    }

    public Field queryPlates(Set<QueryPlate> queryPlates) {
        this.queryPlates = queryPlates;
        return this;
    }

    public Field addQueryPlate(QueryPlate queryPlate) {
        this.queryPlates.add(queryPlate);
        queryPlate.setField(this);
        return this;
    }

    public Field removeQueryPlate(QueryPlate queryPlate) {
        this.queryPlates.remove(queryPlate);
        queryPlate.setField(null);
        return this;
    }

    public void setQueryPlates(Set<QueryPlate> queryPlates) {
        this.queryPlates = queryPlates;
    }

    public Set<MalaParam> getMalaParams() {
        return malaParams;
    }

    public Field malaParams(Set<MalaParam> malaParams) {
        this.malaParams = malaParams;
        return this;
    }

    public Field addMalaParam(MalaParam malaParam) {
        this.malaParams.add(malaParam);
        malaParam.setField(this);
        return this;
    }

    public Field removeMalaParam(MalaParam malaParam) {
        this.malaParams.remove(malaParam);
        malaParam.setField(null);
        return this;
    }

    public void setMalaParams(Set<MalaParam> malaParams) {
        this.malaParams = malaParams;
    }

    public CascaderParam getDataSource() {
        return dataSource;
    }

    public Field dataSource(CascaderParam cascaderParam) {
        this.dataSource = cascaderParam;
        return this;
    }

    public void setDataSource(CascaderParam cascaderParam) {
        this.dataSource = cascaderParam;
    }

    public CascaderParam getDataType() {
        return dataType;
    }

    public Field dataType(CascaderParam cascaderParam) {
        this.dataType = cascaderParam;
        return this;
    }

    public void setDataType(CascaderParam cascaderParam) {
        this.dataType = cascaderParam;
    }

    public Cascader getCascader() {
        return cascader;
    }

    public Field cascader(Cascader cascader) {
        this.cascader = cascader;
        return this;
    }

    public void setCascader(Cascader cascader) {
        this.cascader = cascader;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Field)) {
            return false;
        }
        return id != null && id.equals(((Field) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Field{" +
            "id=" + getId() +
            ", fieldName='" + getFieldName() + "'" +
            ", peckOrder=" + getPeckOrder() +
            "}";
    }
}
