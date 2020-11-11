package com.neemshade.matri.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CascaderParamMapperTest {

    private CascaderParamMapper cascaderParamMapper;

    @BeforeEach
    public void setUp() {
        cascaderParamMapper = new CascaderParamMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(cascaderParamMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(cascaderParamMapper.fromId(null)).isNull();
    }
}
