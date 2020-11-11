package com.neemshade.matri.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * single query
 */
@Entity
@Table(name = "query_plate")
public class QueryPlate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_range")
    private Boolean isRange;

    @Column(name = "is_multi")
    private Boolean isMulti;

    @Column(name = "peck_order")
    private Integer peckOrder;

    @OneToMany(mappedBy = "queryPlate")
    private Set<PlateParam> plateParams = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "queryPlates", allowSetters = true)
    private Query query;

    @ManyToOne
    @JsonIgnoreProperties(value = "queryPlates", allowSetters = true)
    private Field field;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isIsRange() {
        return isRange;
    }

    public QueryPlate isRange(Boolean isRange) {
        this.isRange = isRange;
        return this;
    }

    public void setIsRange(Boolean isRange) {
        this.isRange = isRange;
    }

    public Boolean isIsMulti() {
        return isMulti;
    }

    public QueryPlate isMulti(Boolean isMulti) {
        this.isMulti = isMulti;
        return this;
    }

    public void setIsMulti(Boolean isMulti) {
        this.isMulti = isMulti;
    }

    public Integer getPeckOrder() {
        return peckOrder;
    }

    public QueryPlate peckOrder(Integer peckOrder) {
        this.peckOrder = peckOrder;
        return this;
    }

    public void setPeckOrder(Integer peckOrder) {
        this.peckOrder = peckOrder;
    }

    public Set<PlateParam> getPlateParams() {
        return plateParams;
    }

    public QueryPlate plateParams(Set<PlateParam> plateParams) {
        this.plateParams = plateParams;
        return this;
    }

    public QueryPlate addPlateParam(PlateParam plateParam) {
        this.plateParams.add(plateParam);
        plateParam.setQueryPlate(this);
        return this;
    }

    public QueryPlate removePlateParam(PlateParam plateParam) {
        this.plateParams.remove(plateParam);
        plateParam.setQueryPlate(null);
        return this;
    }

    public void setPlateParams(Set<PlateParam> plateParams) {
        this.plateParams = plateParams;
    }

    public Query getQuery() {
        return query;
    }

    public QueryPlate query(Query query) {
        this.query = query;
        return this;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public Field getField() {
        return field;
    }

    public QueryPlate field(Field field) {
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
        if (!(o instanceof QueryPlate)) {
            return false;
        }
        return id != null && id.equals(((QueryPlate) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QueryPlate{" +
            "id=" + getId() +
            ", isRange='" + isIsRange() + "'" +
            ", isMulti='" + isIsMulti() + "'" +
            ", peckOrder=" + getPeckOrder() +
            "}";
    }
}
