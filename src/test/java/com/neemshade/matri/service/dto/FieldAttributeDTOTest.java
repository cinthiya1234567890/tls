package com.neemshade.matri.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.neemshade.matri.web.rest.TestUtil;

public class FieldAttributeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FieldAttributeDTO.class);
        FieldAttributeDTO fieldAttributeDTO1 = new FieldAttributeDTO();
        fieldAttributeDTO1.setId(1L);
        FieldAttributeDTO fieldAttributeDTO2 = new FieldAttributeDTO();
        assertThat(fieldAttributeDTO1).isNotEqualTo(fieldAttributeDTO2);
        fieldAttributeDTO2.setId(fieldAttributeDTO1.getId());
        assertThat(fieldAttributeDTO1).isEqualTo(fieldAttributeDTO2);
        fieldAttributeDTO2.setId(2L);
        assertThat(fieldAttributeDTO1).isNotEqualTo(fieldAttributeDTO2);
        fieldAttributeDTO1.setId(null);
        assertThat(fieldAttributeDTO1).isNotEqualTo(fieldAttributeDTO2);
    }
}
