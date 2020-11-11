package com.neemshade.matri.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.neemshade.matri.web.rest.TestUtil;

public class VitalTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Vital.class);
        Vital vital1 = new Vital();
        vital1.setId(1L);
        Vital vital2 = new Vital();
        vital2.setId(vital1.getId());
        assertThat(vital1).isEqualTo(vital2);
        vital2.setId(2L);
        assertThat(vital1).isNotEqualTo(vital2);
        vital1.setId(null);
        assertThat(vital1).isNotEqualTo(vital2);
    }
}
