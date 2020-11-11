package com.neemshade.matri.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PlateParamMapperTest {

    private PlateParamMapper plateParamMapper;

    @BeforeEach
    public void setUp() {
        plateParamMapper = new PlateParamMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(plateParamMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(plateParamMapper.fromId(null)).isNull();
    }
}
