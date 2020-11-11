package com.neemshade.matri.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.neemshade.matri.web.rest.TestUtil;

public class PlateParamDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlateParamDTO.class);
        PlateParamDTO plateParamDTO1 = new PlateParamDTO();
        plateParamDTO1.setId(1L);
        PlateParamDTO plateParamDTO2 = new PlateParamDTO();
        assertThat(plateParamDTO1).isNotEqualTo(plateParamDTO2);
        plateParamDTO2.setId(plateParamDTO1.getId());
        assertThat(plateParamDTO1).isEqualTo(plateParamDTO2);
        plateParamDTO2.setId(2L);
        assertThat(plateParamDTO1).isNotEqualTo(plateParamDTO2);
        plateParamDTO1.setId(null);
        assertThat(plateParamDTO1).isNotEqualTo(plateParamDTO2);
    }
}
