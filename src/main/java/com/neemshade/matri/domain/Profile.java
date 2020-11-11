package com.neemshade.matri.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * record of one person
 */
@Entity
@Table(name = "profile")
public class Profile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "phone")
    private String phone;

    @Column(name = "created_time")
    private Instant createdTime;

    @Column(name = "login_time")
    private Instant loginTime;

    @Column(name = "prev_login_time")
    private Instant prevLoginTime;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    @OneToMany(mappedBy = "profile")
    private Set<ProfileParam> profileParams = new HashSet<>();

    @OneToMany(mappedBy = "profile")
    private Set<Photo> photos = new HashSet<>();

    @OneToMany(mappedBy = "sender")
    private Set<Contact> sents = new HashSet<>();

    @OneToMany(mappedBy = "receiver")
    private Set<Contact> receiveds = new HashSet<>();

    @OneToMany(mappedBy = "profile")
    private Set<Query> queries = new HashSet<>();

    @OneToMany(mappedBy = "payer")
    private Set<Payment> payments = new HashSet<>();

    @OneToMany(mappedBy = "complaint")
    private Set<Issue> issueds = new HashSet<>();

    @OneToMany(mappedBy = "admin")
    private Set<Issue> addresseds = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Profile name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Profile dateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public Profile phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Instant getCreatedTime() {
        return createdTime;
    }

    public Profile createdTime(Instant createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public void setCreatedTime(Instant createdTime) {
        this.createdTime = createdTime;
    }

    public Instant getLoginTime() {
        return loginTime;
    }

    public Profile loginTime(Instant loginTime) {
        this.loginTime = loginTime;
        return this;
    }

    public void setLoginTime(Instant loginTime) {
        this.loginTime = loginTime;
    }

    public Instant getPrevLoginTime() {
        return prevLoginTime;
    }

    public Profile prevLoginTime(Instant prevLoginTime) {
        this.prevLoginTime = prevLoginTime;
        return this;
    }

    public void setPrevLoginTime(Instant prevLoginTime) {
        this.prevLoginTime = prevLoginTime;
    }

    public User getUser() {
        return user;
    }

    public Profile user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<ProfileParam> getProfileParams() {
        return profileParams;
    }

    public Profile profileParams(Set<ProfileParam> profileParams) {
        this.profileParams = profileParams;
        return this;
    }

    public Profile addProfileParam(ProfileParam profileParam) {
        this.profileParams.add(profileParam);
        profileParam.setProfile(this);
        return this;
    }

    public Profile removeProfileParam(ProfileParam profileParam) {
        this.profileParams.remove(profileParam);
        profileParam.setProfile(null);
        return this;
    }

    public void setProfileParams(Set<ProfileParam> profileParams) {
        this.profileParams = profileParams;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public Profile photos(Set<Photo> photos) {
        this.photos = photos;
        return this;
    }

    public Profile addPhoto(Photo photo) {
        this.photos.add(photo);
        photo.setProfile(this);
        return this;
    }

    public Profile removePhoto(Photo photo) {
        this.photos.remove(photo);
        photo.setProfile(null);
        return this;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    public Set<Contact> getSents() {
        return sents;
    }

    public Profile sents(Set<Contact> contacts) {
        this.sents = contacts;
        return this;
    }

    public Profile addSent(Contact contact) {
        this.sents.add(contact);
        contact.setSender(this);
        return this;
    }

    public Profile removeSent(Contact contact) {
        this.sents.remove(contact);
        contact.setSender(null);
        return this;
    }

    public void setSents(Set<Contact> contacts) {
        this.sents = contacts;
    }

    public Set<Contact> getReceiveds() {
        return receiveds;
    }

    public Profile receiveds(Set<Contact> contacts) {
        this.receiveds = contacts;
        return this;
    }

    public Profile addReceived(Contact contact) {
        this.receiveds.add(contact);
        contact.setReceiver(this);
        return this;
    }

    public Profile removeReceived(Contact contact) {
        this.receiveds.remove(contact);
        contact.setReceiver(null);
        return this;
    }

    public void setReceiveds(Set<Contact> contacts) {
        this.receiveds = contacts;
    }

    public Set<Query> getQueries() {
        return queries;
    }

    public Profile queries(Set<Query> queries) {
        this.queries = queries;
        return this;
    }

    public Profile addQuery(Query query) {
        this.queries.add(query);
        query.setProfile(this);
        return this;
    }

    public Profile removeQuery(Query query) {
        this.queries.remove(query);
        query.setProfile(null);
        return this;
    }

    public void setQueries(Set<Query> queries) {
        this.queries = queries;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public Profile payments(Set<Payment> payments) {
        this.payments = payments;
        return this;
    }

    public Profile addPayment(Payment payment) {
        this.payments.add(payment);
        payment.setPayer(this);
        return this;
    }

    public Profile removePayment(Payment payment) {
        this.payments.remove(payment);
        payment.setPayer(null);
        return this;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    public Set<Issue> getIssueds() {
        return issueds;
    }

    public Profile issueds(Set<Issue> issues) {
        this.issueds = issues;
        return this;
    }

    public Profile addIssued(Issue issue) {
        this.issueds.add(issue);
        issue.setComplaint(this);
        return this;
    }

    public Profile removeIssued(Issue issue) {
        this.issueds.remove(issue);
        issue.setComplaint(null);
        return this;
    }

    public void setIssueds(Set<Issue> issues) {
        this.issueds = issues;
    }

    public Set<Issue> getAddresseds() {
        return addresseds;
    }

    public Profile addresseds(Set<Issue> issues) {
        this.addresseds = issues;
        return this;
    }

    public Profile addAddressed(Issue issue) {
        this.addresseds.add(issue);
        issue.setAdmin(this);
        return this;
    }

    public Profile removeAddressed(Issue issue) {
        this.addresseds.remove(issue);
        issue.setAdmin(null);
        return this;
    }

    public void setAddresseds(Set<Issue> issues) {
        this.addresseds = issues;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Profile)) {
            return false;
        }
        return id != null && id.equals(((Profile) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Profile{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", phone='" + getPhone() + "'" +
            ", createdTime='" + getCreatedTime() + "'" +
            ", loginTime='" + getLoginTime() + "'" +
            ", prevLoginTime='" + getPrevLoginTime() + "'" +
            "}";
    }
}
