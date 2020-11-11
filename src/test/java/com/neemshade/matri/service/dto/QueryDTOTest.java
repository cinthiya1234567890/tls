package com.neemshade.matri.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.neemshade.matri.web.rest.TestUtil;

public class QueryDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QueryDTO.class);
        QueryDTO queryDTO1 = new QueryDTO();
        queryDTO1.setId(1L);
        QueryDTO queryDTO2 = new QueryDTO();
        assertThat(queryDTO1).isNotEqualTo(queryDTO2);
        queryDTO2.setId(queryDTO1.getId());
        assertThat(queryDTO1).isEqualTo(queryDTO2);
        queryDTO2.setId(2L);
        assertThat(queryDTO1).isNotEqualTo(queryDTO2);
        queryDTO1.setId(null);
        assertThat(queryDTO1).isNotEqualTo(queryDTO2);
    }
}
