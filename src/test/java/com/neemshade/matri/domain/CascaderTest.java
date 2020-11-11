package com.neemshade.matri.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.neemshade.matri.web.rest.TestUtil;

public class CascaderTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Cascader.class);
        Cascader cascader1 = new Cascader();
        cascader1.setId(1L);
        Cascader cascader2 = new Cascader();
        cascader2.setId(cascader1.getId());
        assertThat(cascader1).isEqualTo(cascader2);
        cascader2.setId(2L);
        assertThat(cascader1).isNotEqualTo(cascader2);
        cascader1.setId(null);
        assertThat(cascader1).isNotEqualTo(cascader2);
    }
}
