package com.neemshade.matri.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CascaderMapperTest {

    private CascaderMapper cascaderMapper;

    @BeforeEach
    public void setUp() {
        cascaderMapper = new CascaderMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(cascaderMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(cascaderMapper.fromId(null)).isNull();
    }
}
