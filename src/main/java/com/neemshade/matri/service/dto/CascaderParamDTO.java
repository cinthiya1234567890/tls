package com.neemshade.matri.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;

/**
 * A DTO for the {@link com.neemshade.matri.domain.CascaderParam} entity.
 */
@ApiModel(description = "entry for single param value")
public class CascaderParamDTO implements Serializable {
    
    private Long id;

    private String paramTitle;

    private Integer peckOrder;

    private Integer levelIndex;


    private Long parentId;

    private Long cascaderId;

    private String cascaderCascaderName;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParamTitle() {
        return paramTitle;
    }

    public void setParamTitle(String paramTitle) {
        this.paramTitle = paramTitle;
    }

    public Integer getPeckOrder() {
        return peckOrder;
    }

    public void setPeckOrder(Integer peckOrder) {
        this.peckOrder = peckOrder;
    }

    public Integer getLevelIndex() {
        return levelIndex;
    }

    public void setLevelIndex(Integer levelIndex) {
        this.levelIndex = levelIndex;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long cascaderParamId) {
        this.parentId = cascaderParamId;
    }

    public Long getCascaderId() {
        return cascaderId;
    }

    public void setCascaderId(Long cascaderId) {
        this.cascaderId = cascaderId;
    }

    public String getCascaderCascaderName() {
        return cascaderCascaderName;
    }

    public void setCascaderCascaderName(String cascaderCascaderName) {
        this.cascaderCascaderName = cascaderCascaderName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CascaderParamDTO)) {
            return false;
        }

        return id != null && id.equals(((CascaderParamDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CascaderParamDTO{" +
            "id=" + getId() +
            ", paramTitle='" + getParamTitle() + "'" +
            ", peckOrder=" + getPeckOrder() +
            ", levelIndex=" + getLevelIndex() +
            ", parentId=" + getParentId() +
            ", cascaderId=" + getCascaderId() +
            ", cascaderCascaderName='" + getCascaderCascaderName() + "'" +
            "}";
    }
}
