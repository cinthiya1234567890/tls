package com.neemshade.matri.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.neemshade.matri.web.rest.TestUtil;

public class MalaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MalaDTO.class);
        MalaDTO malaDTO1 = new MalaDTO();
        malaDTO1.setId(1L);
        MalaDTO malaDTO2 = new MalaDTO();
        assertThat(malaDTO1).isNotEqualTo(malaDTO2);
        malaDTO2.setId(malaDTO1.getId());
        assertThat(malaDTO1).isEqualTo(malaDTO2);
        malaDTO2.setId(2L);
        assertThat(malaDTO1).isNotEqualTo(malaDTO2);
        malaDTO1.setId(null);
        assertThat(malaDTO1).isNotEqualTo(malaDTO2);
    }
}
