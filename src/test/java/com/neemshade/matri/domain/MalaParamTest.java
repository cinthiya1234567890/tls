package com.neemshade.matri.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.neemshade.matri.web.rest.TestUtil;

public class MalaParamTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MalaParam.class);
        MalaParam malaParam1 = new MalaParam();
        malaParam1.setId(1L);
        MalaParam malaParam2 = new MalaParam();
        malaParam2.setId(malaParam1.getId());
        assertThat(malaParam1).isEqualTo(malaParam2);
        malaParam2.setId(2L);
        assertThat(malaParam1).isNotEqualTo(malaParam2);
        malaParam1.setId(null);
        assertThat(malaParam1).isNotEqualTo(malaParam2);
    }
}
