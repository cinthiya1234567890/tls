package com.neemshade.matri.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.neemshade.matri.web.rest.TestUtil;

public class MalaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Mala.class);
        Mala mala1 = new Mala();
        mala1.setId(1L);
        Mala mala2 = new Mala();
        mala2.setId(mala1.getId());
        assertThat(mala1).isEqualTo(mala2);
        mala2.setId(2L);
        assertThat(mala1).isNotEqualTo(mala2);
        mala1.setId(null);
        assertThat(mala1).isNotEqualTo(mala2);
    }
}
