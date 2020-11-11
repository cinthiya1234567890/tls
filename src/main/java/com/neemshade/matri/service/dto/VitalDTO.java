package com.neemshade.matri.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;

/**
 * A DTO for the {@link com.neemshade.matri.domain.Vital} entity.
 */
@ApiModel(description = "useful data related to website.  eg\nagencyBanner, webAdminEmail, numOfPhotos\nnumberOfMonthsPerTerm, termFees")
public class VitalDTO implements Serializable {
    
    private Long id;

    private String vitalName;

    private String vitalValue;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVitalName() {
        return vitalName;
    }

    public void setVitalName(String vitalName) {
        this.vitalName = vitalName;
    }

    public String getVitalValue() {
        return vitalValue;
    }

    public void setVitalValue(String vitalValue) {
        this.vitalValue = vitalValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VitalDTO)) {
            return false;
        }

        return id != null && id.equals(((VitalDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VitalDTO{" +
            "id=" + getId() +
            ", vitalName='" + getVitalName() + "'" +
            ", vitalValue='" + getVitalValue() + "'" +
            "}";
    }
}
