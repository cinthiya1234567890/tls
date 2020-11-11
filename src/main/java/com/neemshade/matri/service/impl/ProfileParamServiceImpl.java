package com.neemshade.matri.service.impl;

import com.neemshade.matri.service.ProfileParamService;
import com.neemshade.matri.domain.ProfileParam;
import com.neemshade.matri.repository.ProfileParamRepository;
import com.neemshade.matri.service.dto.ProfileParamDTO;
import com.neemshade.matri.service.mapper.ProfileParamMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ProfileParam}.
 */
@Service
@Transactional
public class ProfileParamServiceImpl implements ProfileParamService {

    private final Logger log = LoggerFactory.getLogger(ProfileParamServiceImpl.class);

    private final ProfileParamRepository profileParamRepository;

    private final ProfileParamMapper profileParamMapper;

    public ProfileParamServiceImpl(ProfileParamRepository profileParamRepository, ProfileParamMapper profileParamMapper) {
        this.profileParamRepository = profileParamRepository;
        this.profileParamMapper = profileParamMapper;
    }

    @Override
    public ProfileParamDTO save(ProfileParamDTO profileParamDTO) {
        log.debug("Request to save ProfileParam : {}", profileParamDTO);
        ProfileParam profileParam = profileParamMapper.toEntity(profileParamDTO);
        profileParam = profileParamRepository.save(profileParam);
        return profileParamMapper.toDto(profileParam);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProfileParamDTO> findAll() {
        log.debug("Request to get all ProfileParams");
        return profileParamRepository.findAll().stream()
            .map(profileParamMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ProfileParamDTO> findOne(Long id) {
        log.debug("Request to get ProfileParam : {}", id);
        return profileParamRepository.findById(id)
            .map(profileParamMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProfileParam : {}", id);
        profileParamRepository.deleteById(id);
    }
}
