package com.neemshade.matri.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FieldMapperTest {

    private FieldMapper fieldMapper;

    @BeforeEach
    public void setUp() {
        fieldMapper = new FieldMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(fieldMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(fieldMapper.fromId(null)).isNull();
    }
}
