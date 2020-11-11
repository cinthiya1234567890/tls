package com.neemshade.matri.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.neemshade.matri.web.rest.TestUtil;

public class QueryPlateDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QueryPlateDTO.class);
        QueryPlateDTO queryPlateDTO1 = new QueryPlateDTO();
        queryPlateDTO1.setId(1L);
        QueryPlateDTO queryPlateDTO2 = new QueryPlateDTO();
        assertThat(queryPlateDTO1).isNotEqualTo(queryPlateDTO2);
        queryPlateDTO2.setId(queryPlateDTO1.getId());
        assertThat(queryPlateDTO1).isEqualTo(queryPlateDTO2);
        queryPlateDTO2.setId(2L);
        assertThat(queryPlateDTO1).isNotEqualTo(queryPlateDTO2);
        queryPlateDTO1.setId(null);
        assertThat(queryPlateDTO1).isNotEqualTo(queryPlateDTO2);
    }
}
