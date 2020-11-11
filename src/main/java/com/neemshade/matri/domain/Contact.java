package com.neemshade.matri.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * when a profile pass interest, decline, match are stored in Contact table
 */
@Entity
@Table(name = "contact")
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "initiated_date")
    private Instant initiatedDate;

    @Column(name = "updated_date")
    private Instant updatedDate;

    @ManyToOne
    @JsonIgnoreProperties(value = "contacts", allowSetters = true)
    private CascaderParam contactStatus;

    @ManyToOne
    @JsonIgnoreProperties(value = "sents", allowSetters = true)
    private Profile sender;

    @ManyToOne
    @JsonIgnoreProperties(value = "receiveds", allowSetters = true)
    private Profile receiver;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getInitiatedDate() {
        return initiatedDate;
    }

    public Contact initiatedDate(Instant initiatedDate) {
        this.initiatedDate = initiatedDate;
        return this;
    }

    public void setInitiatedDate(Instant initiatedDate) {
        this.initiatedDate = initiatedDate;
    }

    public Instant getUpdatedDate() {
        return updatedDate;
    }

    public Contact updatedDate(Instant updatedDate) {
        this.updatedDate = updatedDate;
        return this;
    }

    public void setUpdatedDate(Instant updatedDate) {
        this.updatedDate = updatedDate;
    }

    public CascaderParam getContactStatus() {
        return contactStatus;
    }

    public Contact contactStatus(CascaderParam cascaderParam) {
        this.contactStatus = cascaderParam;
        return this;
    }

    public void setContactStatus(CascaderParam cascaderParam) {
        this.contactStatus = cascaderParam;
    }

    public Profile getSender() {
        return sender;
    }

    public Contact sender(Profile profile) {
        this.sender = profile;
        return this;
    }

    public void setSender(Profile profile) {
        this.sender = profile;
    }

    public Profile getReceiver() {
        return receiver;
    }

    public Contact receiver(Profile profile) {
        this.receiver = profile;
        return this;
    }

    public void setReceiver(Profile profile) {
        this.receiver = profile;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Contact)) {
            return false;
        }
        return id != null && id.equals(((Contact) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Contact{" +
            "id=" + getId() +
            ", initiatedDate='" + getInitiatedDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            "}";
    }
}
