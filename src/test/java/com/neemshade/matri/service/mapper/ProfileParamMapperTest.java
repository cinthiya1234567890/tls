package com.neemshade.matri.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ProfileParamMapperTest {

    private ProfileParamMapper profileParamMapper;

    @BeforeEach
    public void setUp() {
        profileParamMapper = new ProfileParamMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(profileParamMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(profileParamMapper.fromId(null)).isNull();
    }
}
