package com.neemshade.matri.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.neemshade.matri.web.rest.TestUtil;

public class CascaderParamDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CascaderParamDTO.class);
        CascaderParamDTO cascaderParamDTO1 = new CascaderParamDTO();
        cascaderParamDTO1.setId(1L);
        CascaderParamDTO cascaderParamDTO2 = new CascaderParamDTO();
        assertThat(cascaderParamDTO1).isNotEqualTo(cascaderParamDTO2);
        cascaderParamDTO2.setId(cascaderParamDTO1.getId());
        assertThat(cascaderParamDTO1).isEqualTo(cascaderParamDTO2);
        cascaderParamDTO2.setId(2L);
        assertThat(cascaderParamDTO1).isNotEqualTo(cascaderParamDTO2);
        cascaderParamDTO1.setId(null);
        assertThat(cascaderParamDTO1).isNotEqualTo(cascaderParamDTO2);
    }
}
