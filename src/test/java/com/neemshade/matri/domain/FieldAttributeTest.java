package com.neemshade.matri.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.neemshade.matri.web.rest.TestUtil;

public class FieldAttributeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FieldAttribute.class);
        FieldAttribute fieldAttribute1 = new FieldAttribute();
        fieldAttribute1.setId(1L);
        FieldAttribute fieldAttribute2 = new FieldAttribute();
        fieldAttribute2.setId(fieldAttribute1.getId());
        assertThat(fieldAttribute1).isEqualTo(fieldAttribute2);
        fieldAttribute2.setId(2L);
        assertThat(fieldAttribute1).isNotEqualTo(fieldAttribute2);
        fieldAttribute1.setId(null);
        assertThat(fieldAttribute1).isNotEqualTo(fieldAttribute2);
    }
}
