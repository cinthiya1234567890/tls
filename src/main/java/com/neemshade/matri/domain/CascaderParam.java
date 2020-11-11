package com.neemshade.matri.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * entry for single param value
 */
@Entity
@Table(name = "cascader_param")
public class CascaderParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "param_title")
    private String paramTitle;

    @Column(name = "peck_order")
    private Integer peckOrder;

    @Column(name = "level_index")
    private Integer levelIndex;

    @OneToMany(mappedBy = "dataSource")
    private Set<Field> dataSourcers = new HashSet<>();

    @OneToMany(mappedBy = "dataType")
    private Set<Field> dataTypers = new HashSet<>();

    @OneToMany(mappedBy = "cascaderParam")
    private Set<ProfileParam> profileParams = new HashSet<>();

    @OneToMany(mappedBy = "contactStatus")
    private Set<Contact> contacts = new HashSet<>();

    @OneToMany(mappedBy = "parent")
    private Set<CascaderParam> children = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "children", allowSetters = true)
    private CascaderParam parent;

    @ManyToOne
    @JsonIgnoreProperties(value = "cascaderParams", allowSetters = true)
    private Cascader cascader;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParamTitle() {
        return paramTitle;
    }

    public CascaderParam paramTitle(String paramTitle) {
        this.paramTitle = paramTitle;
        return this;
    }

    public void setParamTitle(String paramTitle) {
        this.paramTitle = paramTitle;
    }

    public Integer getPeckOrder() {
        return peckOrder;
    }

    public CascaderParam peckOrder(Integer peckOrder) {
        this.peckOrder = peckOrder;
        return this;
    }

    public void setPeckOrder(Integer peckOrder) {
        this.peckOrder = peckOrder;
    }

    public Integer getLevelIndex() {
        return levelIndex;
    }

    public CascaderParam levelIndex(Integer levelIndex) {
        this.levelIndex = levelIndex;
        return this;
    }

    public void setLevelIndex(Integer levelIndex) {
        this.levelIndex = levelIndex;
    }

    public Set<Field> getDataSourcers() {
        return dataSourcers;
    }

    public CascaderParam dataSourcers(Set<Field> fields) {
        this.dataSourcers = fields;
        return this;
    }

    public CascaderParam addDataSourcer(Field field) {
        this.dataSourcers.add(field);
        field.setDataSource(this);
        return this;
    }

    public CascaderParam removeDataSourcer(Field field) {
        this.dataSourcers.remove(field);
        field.setDataSource(null);
        return this;
    }

    public void setDataSourcers(Set<Field> fields) {
        this.dataSourcers = fields;
    }

    public Set<Field> getDataTypers() {
        return dataTypers;
    }

    public CascaderParam dataTypers(Set<Field> fields) {
        this.dataTypers = fields;
        return this;
    }

    public CascaderParam addDataTyper(Field field) {
        this.dataTypers.add(field);
        field.setDataType(this);
        return this;
    }

    public CascaderParam removeDataTyper(Field field) {
        this.dataTypers.remove(field);
        field.setDataType(null);
        return this;
    }

    public void setDataTypers(Set<Field> fields) {
        this.dataTypers = fields;
    }

    public Set<ProfileParam> getProfileParams() {
        return profileParams;
    }

    public CascaderParam profileParams(Set<ProfileParam> profileParams) {
        this.profileParams = profileParams;
        return this;
    }

    public CascaderParam addProfileParam(ProfileParam profileParam) {
        this.profileParams.add(profileParam);
        profileParam.setCascaderParam(this);
        return this;
    }

    public CascaderParam removeProfileParam(ProfileParam profileParam) {
        this.profileParams.remove(profileParam);
        profileParam.setCascaderParam(null);
        return this;
    }

    public void setProfileParams(Set<ProfileParam> profileParams) {
        this.profileParams = profileParams;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public CascaderParam contacts(Set<Contact> contacts) {
        this.contacts = contacts;
        return this;
    }

    public CascaderParam addContact(Contact contact) {
        this.contacts.add(contact);
        contact.setContactStatus(this);
        return this;
    }

    public CascaderParam removeContact(Contact contact) {
        this.contacts.remove(contact);
        contact.setContactStatus(null);
        return this;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    public Set<CascaderParam> getChildren() {
        return children;
    }

    public CascaderParam children(Set<CascaderParam> cascaderParams) {
        this.children = cascaderParams;
        return this;
    }

    public CascaderParam addChild(CascaderParam cascaderParam) {
        this.children.add(cascaderParam);
        cascaderParam.setParent(this);
        return this;
    }

    public CascaderParam removeChild(CascaderParam cascaderParam) {
        this.children.remove(cascaderParam);
        cascaderParam.setParent(null);
        return this;
    }

    public void setChildren(Set<CascaderParam> cascaderParams) {
        this.children = cascaderParams;
    }

    public CascaderParam getParent() {
        return parent;
    }

    public CascaderParam parent(CascaderParam cascaderParam) {
        this.parent = cascaderParam;
        return this;
    }

    public void setParent(CascaderParam cascaderParam) {
        this.parent = cascaderParam;
    }

    public Cascader getCascader() {
        return cascader;
    }

    public CascaderParam cascader(Cascader cascader) {
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
        if (!(o instanceof CascaderParam)) {
            return false;
        }
        return id != null && id.equals(((CascaderParam) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CascaderParam{" +
            "id=" + getId() +
            ", paramTitle='" + getParamTitle() + "'" +
            ", peckOrder=" + getPeckOrder() +
            ", levelIndex=" + getLevelIndex() +
            "}";
    }
}
