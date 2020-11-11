package com.neemshade.matri.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.neemshade.matri.web.rest.TestUtil;

public class ProfileParamTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProfileParam.class);
        ProfileParam profileParam1 = new ProfileParam();
        profileParam1.setId(1L);
        ProfileParam profileParam2 = new ProfileParam();
        profileParam2.setId(profileParam1.getId());
        assertThat(profileParam1).isEqualTo(profileParam2);
        profileParam2.setId(2L);
        assertThat(profileParam1).isNotEqualTo(profileParam2);
        profileParam1.setId(null);
        assertThat(profileParam1).isNotEqualTo(profileParam2);
    }
}
