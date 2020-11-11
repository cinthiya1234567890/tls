package com.neemshade.matri.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MalaMapperTest {

    private MalaMapper malaMapper;

    @BeforeEach
    public void setUp() {
        malaMapper = new MalaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(malaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(malaMapper.fromId(null)).isNull();
    }
}
