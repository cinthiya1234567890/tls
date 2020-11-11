package com.neemshade.matri.domain;


import javax.persistence.*;

import java.io.Serializable;

/**
 * useful data related to website.  eg\nagencyBanner, webAdminEmail, numOfPhotos\nnumberOfMonthsPerTerm, termFees
 */
@Entity
@Table(name = "vital")
public class Vital implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vital_name")
    private String vitalName;

    @Column(name = "vital_value")
    private String vitalValue;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVitalName() {
        return vitalName;
    }

    public Vital vitalName(String vitalName) {
        this.vitalName = vitalName;
        return this;
    }

    public void setVitalName(String vitalName) {
        this.vitalName = vitalName;
    }

    public String getVitalValue() {
        return vitalValue;
    }

    public Vital vitalValue(String vitalValue) {
        this.vitalValue = vitalValue;
        return this;
    }

    public void setVitalValue(String vitalValue) {
        this.vitalValue = vitalValue;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vital)) {
            return false;
        }
        return id != null && id.equals(((Vital) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Vital{" +
            "id=" + getId() +
            ", vitalName='" + getVitalName() + "'" +
            ", vitalValue='" + getVitalValue() + "'" +
            "}";
    }
}
