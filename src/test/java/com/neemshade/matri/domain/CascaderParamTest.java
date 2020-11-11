package com.neemshade.matri.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.neemshade.matri.web.rest.TestUtil;

public class CascaderParamTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CascaderParam.class);
        CascaderParam cascaderParam1 = new CascaderParam();
        cascaderParam1.setId(1L);
        CascaderParam cascaderParam2 = new CascaderParam();
        cascaderParam2.setId(cascaderParam1.getId());
        assertThat(cascaderParam1).isEqualTo(cascaderParam2);
        cascaderParam2.setId(2L);
        assertThat(cascaderParam1).isNotEqualTo(cascaderParam2);
        cascaderParam1.setId(null);
        assertThat(cascaderParam1).isNotEqualTo(cascaderParam2);
    }
}
