package com.neemshade.matri.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * a collection of queries placed together\nfor eg, \"profiles in my district\" and \"mother tongue tamil, telugu\"
 */
@Entity
@Table(name = "query")
public class Query implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "query_name")
    private String queryName;

    @Column(name = "peck_order")
    private Integer peckOrder;

    @OneToMany(mappedBy = "query")
    private Set<QueryPlate> queryPlates = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "queries", allowSetters = true)
    private Profile profile;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQueryName() {
        return queryName;
    }

    public Query queryName(String queryName) {
        this.queryName = queryName;
        return this;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public Integer getPeckOrder() {
        return peckOrder;
    }

    public Query peckOrder(Integer peckOrder) {
        this.peckOrder = peckOrder;
        return this;
    }

    public void setPeckOrder(Integer peckOrder) {
        this.peckOrder = peckOrder;
    }

    public Set<QueryPlate> getQueryPlates() {
        return queryPlates;
    }

    public Query queryPlates(Set<QueryPlate> queryPlates) {
        this.queryPlates = queryPlates;
        return this;
    }

    public Query addQueryPlate(QueryPlate queryPlate) {
        this.queryPlates.add(queryPlate);
        queryPlate.setQuery(this);
        return this;
    }

    public Query removeQueryPlate(QueryPlate queryPlate) {
        this.queryPlates.remove(queryPlate);
        queryPlate.setQuery(null);
        return this;
    }

    public void setQueryPlates(Set<QueryPlate> queryPlates) {
        this.queryPlates = queryPlates;
    }

    public Profile getProfile() {
        return profile;
    }

    public Query profile(Profile profile) {
        this.profile = profile;
        return this;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Query)) {
            return false;
        }
        return id != null && id.equals(((Query) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Query{" +
            "id=" + getId() +
            ", queryName='" + getQueryName() + "'" +
            ", peckOrder=" + getPeckOrder() +
            "}";
    }
}
