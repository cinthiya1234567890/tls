package com.neemshade.matri.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * possible values that a profileData can have\ncascader can be recursive, like location - country - state -city\nthis set of data are stored in Cascader and CascaderParam tables.\n\ncanEnterCustomValue decides if user can place custom value instead of dropdown menu
 */
@Entity
@Table(name = "cascader")
public class Cascader implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cascader_name")
    private String cascaderName;

    @Column(name = "can_enter_custom_value")
    private Boolean canEnterCustomValue;

    @OneToMany(mappedBy = "cascader")
    private Set<Field> fields = new HashSet<>();

    @OneToMany(mappedBy = "cascader")
    private Set<CascaderParam> cascaderParams = new HashSet<>();

    @OneToMany(mappedBy = "cascader")
    private Set<PlateParam> plateParams = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCascaderName() {
        return cascaderName;
    }

    public Cascader cascaderName(String cascaderName) {
        this.cascaderName = cascaderName;
        return this;
    }

    public void setCascaderName(String cascaderName) {
        this.cascaderName = cascaderName;
    }

    public Boolean isCanEnterCustomValue() {
        return canEnterCustomValue;
    }

    public Cascader canEnterCustomValue(Boolean canEnterCustomValue) {
        this.canEnterCustomValue = canEnterCustomValue;
        return this;
    }

    public void setCanEnterCustomValue(Boolean canEnterCustomValue) {
        this.canEnterCustomValue = canEnterCustomValue;
    }

    public Set<Field> getFields() {
        return fields;
    }

    public Cascader fields(Set<Field> fields) {
        this.fields = fields;
        return this;
    }

    public Cascader addField(Field field) {
        this.fields.add(field);
        field.setCascader(this);
        return this;
    }

    public Cascader removeField(Field field) {
        this.fields.remove(field);
        field.setCascader(null);
        return this;
    }

    public void setFields(Set<Field> fields) {
        this.fields = fields;
    }

    public Set<CascaderParam> getCascaderParams() {
        return cascaderParams;
    }

    public Cascader cascaderParams(Set<CascaderParam> cascaderParams) {
        this.cascaderParams = cascaderParams;
        return this;
    }

    public Cascader addCascaderParam(CascaderParam cascaderParam) {
        this.cascaderParams.add(cascaderParam);
        cascaderParam.setCascader(this);
        return this;
    }

    public Cascader removeCascaderParam(CascaderParam cascaderParam) {
        this.cascaderParams.remove(cascaderParam);
        cascaderParam.setCascader(null);
        return this;
    }

    public void setCascaderParams(Set<CascaderParam> cascaderParams) {
        this.cascaderParams = cascaderParams;
    }

    public Set<PlateParam> getPlateParams() {
        return plateParams;
    }

    public Cascader plateParams(Set<PlateParam> plateParams) {
        this.plateParams = plateParams;
        return this;
    }

    public Cascader addPlateParam(PlateParam plateParam) {
        this.plateParams.add(plateParam);
        plateParam.setCascader(this);
        return this;
    }

    public Cascader removePlateParam(PlateParam plateParam) {
        this.plateParams.remove(plateParam);
        plateParam.setCascader(null);
        return this;
    }

    public void setPlateParams(Set<PlateParam> plateParams) {
        this.plateParams = plateParams;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cascader)) {
            return false;
        }
        return id != null && id.equals(((Cascader) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Cascader{" +
            "id=" + getId() +
            ", cascaderName='" + getCascaderName() + "'" +
            ", canEnterCustomValue='" + isCanEnterCustomValue() + "'" +
            "}";
    }
}
