package com.neemshade.matri.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * the query values that are checked for a QueryPlate
 */
@Entity
@Table(name = "plate_param")
public class PlateParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private String value;

    @Column(name = "peck_order")
    private Integer peckOrder;

    @ManyToOne
    @JsonIgnoreProperties(value = "plateParams", allowSetters = true)
    private QueryPlate queryPlate;

    @ManyToOne
    @JsonIgnoreProperties(value = "plateParams", allowSetters = true)
    private Cascader cascader;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public PlateParam value(String value) {
        this.value = value;
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getPeckOrder() {
        return peckOrder;
    }

    public PlateParam peckOrder(Integer peckOrder) {
        this.peckOrder = peckOrder;
        return this;
    }

    public void setPeckOrder(Integer peckOrder) {
        this.peckOrder = peckOrder;
    }

    public QueryPlate getQueryPlate() {
        return queryPlate;
    }

    public PlateParam queryPlate(QueryPlate queryPlate) {
        this.queryPlate = queryPlate;
        return this;
    }

    public void setQueryPlate(QueryPlate queryPlate) {
        this.queryPlate = queryPlate;
    }

    public Cascader getCascader() {
        return cascader;
    }

    public PlateParam cascader(Cascader cascader) {
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
        if (!(o instanceof PlateParam)) {
            return false;
        }
        return id != null && id.equals(((PlateParam) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PlateParam{" +
            "id=" + getId() +
            ", value='" + getValue() + "'" +
            ", peckOrder=" + getPeckOrder() +
            "}";
    }
}
