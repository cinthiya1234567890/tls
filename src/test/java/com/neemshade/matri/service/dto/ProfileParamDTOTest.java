package com.neemshade.matri.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.neemshade.matri.web.rest.TestUtil;

public class ProfileParamDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProfileParamDTO.class);
        ProfileParamDTO profileParamDTO1 = new ProfileParamDTO();
        profileParamDTO1.setId(1L);
        ProfileParamDTO profileParamDTO2 = new ProfileParamDTO();
        assertThat(profileParamDTO1).isNotEqualTo(profileParamDTO2);
        profileParamDTO2.setId(profileParamDTO1.getId());
        assertThat(profileParamDTO1).isEqualTo(profileParamDTO2);
        profileParamDTO2.setId(2L);
        assertThat(profileParamDTO1).isNotEqualTo(profileParamDTO2);
        profileParamDTO1.setId(null);
        assertThat(profileParamDTO1).isNotEqualTo(profileParamDTO2);
    }
}
