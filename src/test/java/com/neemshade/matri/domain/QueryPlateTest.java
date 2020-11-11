package com.neemshade.matri.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.neemshade.matri.web.rest.TestUtil;

public class QueryPlateTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QueryPlate.class);
        QueryPlate queryPlate1 = new QueryPlate();
        queryPlate1.setId(1L);
        QueryPlate queryPlate2 = new QueryPlate();
        queryPlate2.setId(queryPlate1.getId());
        assertThat(queryPlate1).isEqualTo(queryPlate2);
        queryPlate2.setId(2L);
        assertThat(queryPlate1).isNotEqualTo(queryPlate2);
        queryPlate1.setId(null);
        assertThat(queryPlate1).isNotEqualTo(queryPlate2);
    }
}
