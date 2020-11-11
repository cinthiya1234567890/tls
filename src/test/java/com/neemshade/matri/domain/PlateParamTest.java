package com.neemshade.matri.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.neemshade.matri.web.rest.TestUtil;

public class PlateParamTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlateParam.class);
        PlateParam plateParam1 = new PlateParam();
        plateParam1.setId(1L);
        PlateParam plateParam2 = new PlateParam();
        plateParam2.setId(plateParam1.getId());
        assertThat(plateParam1).isEqualTo(plateParam2);
        plateParam2.setId(2L);
        assertThat(plateParam1).isNotEqualTo(plateParam2);
        plateParam1.setId(null);
        assertThat(plateParam1).isNotEqualTo(plateParam2);
    }
}
