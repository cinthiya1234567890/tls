package com.neemshade.matri.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FieldAttributeMapperTest {

    private FieldAttributeMapper fieldAttributeMapper;

    @BeforeEach
    public void setUp() {
        fieldAttributeMapper = new FieldAttributeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(fieldAttributeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(fieldAttributeMapper.fromId(null)).isNull();
    }
}
