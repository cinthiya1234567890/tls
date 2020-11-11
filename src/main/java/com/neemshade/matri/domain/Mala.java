package com.neemshade.matri.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * collection of parameters (fields)
 */
@Entity
@Table(name = "mala")
public class Mala implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mala_name")
    private String malaName;

    @Column(name = "is_editable")
    private Boolean isEditable;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "mala")
    private Set<MalaParam> malaParams = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMalaName() {
        return malaName;
    }

    public Mala malaName(String malaName) {
        this.malaName = malaName;
        return this;
    }

    public void setMalaName(String malaName) {
        this.malaName = malaName;
    }

    public Boolean isIsEditable() {
        return isEditable;
    }

    public Mala isEditable(Boolean isEditable) {
        this.isEditable = isEditable;
        return this;
    }

    public void setIsEditable(Boolean isEditable) {
        this.isEditable = isEditable;
    }

    public String getDescription() {
        return description;
    }

    public Mala description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<MalaParam> getMalaParams() {
        return malaParams;
    }

    public Mala malaParams(Set<MalaParam> malaParams) {
        this.malaParams = malaParams;
        return this;
    }

    public Mala addMalaParam(MalaParam malaParam) {
        this.malaParams.add(malaParam);
        malaParam.setMala(this);
        return this;
    }

    public Mala removeMalaParam(MalaParam malaParam) {
        this.malaParams.remove(malaParam);
        malaParam.setMala(null);
        return this;
    }

    public void setMalaParams(Set<MalaParam> malaParams) {
        this.malaParams = malaParams;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Mala)) {
            return false;
        }
        return id != null && id.equals(((Mala) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Mala{" +
            "id=" + getId() +
            ", malaName='" + getMalaName() + "'" +
            ", isEditable='" + isIsEditable() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
