package com.neemshade.matri.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class VitalMapperTest {

    private VitalMapper vitalMapper;

    @BeforeEach
    public void setUp() {
        vitalMapper = new VitalMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(vitalMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(vitalMapper.fromId(null)).isNull();
    }
}
