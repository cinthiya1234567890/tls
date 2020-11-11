package com.neemshade.matri.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.neemshade.matri.web.rest.TestUtil;

public class MalaParamDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MalaParamDTO.class);
        MalaParamDTO malaParamDTO1 = new MalaParamDTO();
        malaParamDTO1.setId(1L);
        MalaParamDTO malaParamDTO2 = new MalaParamDTO();
        assertThat(malaParamDTO1).isNotEqualTo(malaParamDTO2);
        malaParamDTO2.setId(malaParamDTO1.getId());
        assertThat(malaParamDTO1).isEqualTo(malaParamDTO2);
        malaParamDTO2.setId(2L);
        assertThat(malaParamDTO1).isNotEqualTo(malaParamDTO2);
        malaParamDTO1.setId(null);
        assertThat(malaParamDTO1).isNotEqualTo(malaParamDTO2);
    }
}
