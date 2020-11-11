package com.neemshade.matri.service.dto;

import io.swagger.annotations.ApiModel;
import java.time.Instant;
import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link com.neemshade.matri.domain.Payment} entity.
 */
@ApiModel(description = "history of payment done by a person")
public class PaymentDTO implements Serializable {
    
    private Long id;

    private Instant paymentDate;

    private Integer amount;

    private LocalDate validityDate;

    private String description;


    private Long payerId;

    private String payerName;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Instant paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public LocalDate getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(LocalDate validityDate) {
        this.validityDate = validityDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPayerId() {
        return payerId;
    }

    public void setPayerId(Long profileId) {
        this.payerId = profileId;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String profileName) {
        this.payerName = profileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaymentDTO)) {
            return false;
        }

        return id != null && id.equals(((PaymentDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PaymentDTO{" +
            "id=" + getId() +
            ", paymentDate='" + getPaymentDate() + "'" +
            ", amount=" + getAmount() +
            ", validityDate='" + getValidityDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", payerId=" + getPayerId() +
            ", payerName='" + getPayerName() + "'" +
            "}";
    }
}
