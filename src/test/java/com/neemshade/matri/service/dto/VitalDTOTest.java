package com.neemshade.matri.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.neemshade.matri.web.rest.TestUtil;

public class VitalDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VitalDTO.class);
        VitalDTO vitalDTO1 = new VitalDTO();
        vitalDTO1.setId(1L);
        VitalDTO vitalDTO2 = new VitalDTO();
        assertThat(vitalDTO1).isNotEqualTo(vitalDTO2);
        vitalDTO2.setId(vitalDTO1.getId());
        assertThat(vitalDTO1).isEqualTo(vitalDTO2);
        vitalDTO2.setId(2L);
        assertThat(vitalDTO1).isNotEqualTo(vitalDTO2);
        vitalDTO1.setId(null);
        assertThat(vitalDTO1).isNotEqualTo(vitalDTO2);
    }
}
