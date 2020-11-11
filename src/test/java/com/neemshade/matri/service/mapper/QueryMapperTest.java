package com.neemshade.matri.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class QueryMapperTest {

    private QueryMapper queryMapper;

    @BeforeEach
    public void setUp() {
        queryMapper = new QueryMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(queryMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(queryMapper.fromId(null)).isNull();
    }
}
