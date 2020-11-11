package com.neemshade.matri.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.neemshade.matri.web.rest.TestUtil;

public class CascaderDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CascaderDTO.class);
        CascaderDTO cascaderDTO1 = new CascaderDTO();
        cascaderDTO1.setId(1L);
        CascaderDTO cascaderDTO2 = new CascaderDTO();
        assertThat(cascaderDTO1).isNotEqualTo(cascaderDTO2);
        cascaderDTO2.setId(cascaderDTO1.getId());
        assertThat(cascaderDTO1).isEqualTo(cascaderDTO2);
        cascaderDTO2.setId(2L);
        assertThat(cascaderDTO1).isNotEqualTo(cascaderDTO2);
        cascaderDTO1.setId(null);
        assertThat(cascaderDTO1).isNotEqualTo(cascaderDTO2);
    }
}
