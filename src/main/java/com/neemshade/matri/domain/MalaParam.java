package com.neemshade.matri.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * the list of fields of a Mala
 */
@Entity
@Table(name = "mala_param")
public class MalaParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "peck_order")
    private Integer peckOrder;

    @ManyToOne
    @JsonIgnoreProperties(value = "malaParams", allowSetters = true)
    private Mala mala;

    @ManyToOne
    @JsonIgnoreProperties(value = "malaParams", allowSetters = true)
    private Field field;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPeckOrder() {
        return peckOrder;
    }

    public MalaParam peckOrder(Integer peckOrder) {
        this.peckOrder = peckOrder;
        return this;
    }

    public void setPeckOrder(Integer peckOrder) {
        this.peckOrder = peckOrder;
    }

    public Mala getMala() {
        return mala;
    }

    public MalaParam mala(Mala mala) {
        this.mala = mala;
        return this;
    }

    public void setMala(Mala mala) {
        this.mala = mala;
    }

    public Field getField() {
        return field;
    }

    public MalaParam field(Field field) {
        this.field = field;
        return this;
    }

    public void setField(Field field) {
        this.field = field;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MalaParam)) {
            return false;
        }
        return id != null && id.equals(((MalaParam) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MalaParam{" +
            "id=" + getId() +
            ", peckOrder=" + getPeckOrder() +
            "}";
    }
}
