package com.neemshade.matri.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MalaParamMapperTest {

    private MalaParamMapper malaParamMapper;

    @BeforeEach
    public void setUp() {
        malaParamMapper = new MalaParamMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(malaParamMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(malaParamMapper.fromId(null)).isNull();
    }
}
