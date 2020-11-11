package com.neemshade.matri.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class QueryPlateMapperTest {

    private QueryPlateMapper queryPlateMapper;

    @BeforeEach
    public void setUp() {
        queryPlateMapper = new QueryPlateMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(queryPlateMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(queryPlateMapper.fromId(null)).isNull();
    }
}
