package com.neemshade.matri.service.dto;

import io.swagger.annotations.ApiModel;
import java.time.Instant;
import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link com.neemshade.matri.domain.Profile} entity.
 */
@ApiModel(description = "record of one person")
public class ProfileDTO implements Serializable {
    
    private Long id;

    private String name;

    private LocalDate dateOfBirth;

    private String phone;

    private Instant createdTime;

    private Instant loginTime;

    private Instant prevLoginTime;


    private Long userId;

    private String userLogin;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Instant getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Instant createdTime) {
        this.createdTime = createdTime;
    }

    public Instant getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Instant loginTime) {
        this.loginTime = loginTime;
    }

    public Instant getPrevLoginTime() {
        return prevLoginTime;
    }

    public void setPrevLoginTime(Instant prevLoginTime) {
        this.prevLoginTime = prevLoginTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProfileDTO)) {
            return false;
        }

        return id != null && id.equals(((ProfileDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProfileDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", phone='" + getPhone() + "'" +
            ", createdTime='" + getCreatedTime() + "'" +
            ", loginTime='" + getLoginTime() + "'" +
            ", prevLoginTime='" + getPrevLoginTime() + "'" +
            ", userId=" + getUserId() +
            ", userLogin='" + getUserLogin() + "'" +
            "}";
    }
}
