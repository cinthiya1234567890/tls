package com.neemshade.matri.service.impl;

import com.neemshade.matri.service.PlateParamService;
import com.neemshade.matri.domain.PlateParam;
import com.neemshade.matri.repository.PlateParamRepository;
import com.neemshade.matri.service.dto.PlateParamDTO;
import com.neemshade.matri.service.mapper.PlateParamMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link PlateParam}.
 */
@Service
@Transactional
public class PlateParamServiceImpl implements PlateParamService {

    private final Logger log = LoggerFactory.getLogger(PlateParamServiceImpl.class);

    private final PlateParamRepository plateParamRepository;

    private final PlateParamMapper plateParamMapper;

    public PlateParamServiceImpl(PlateParamRepository plateParamRepository, PlateParamMapper plateParamMapper) {
        this.plateParamRepository = plateParamRepository;
        this.plateParamMapper = plateParamMapper;
    }

    @Override
    public PlateParamDTO save(PlateParamDTO plateParamDTO) {
        log.debug("Request to save PlateParam : {}", plateParamDTO);
        PlateParam plateParam = plateParamMapper.toEntity(plateParamDTO);
        plateParam = plateParamRepository.save(plateParam);
        return plateParamMapper.toDto(plateParam);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlateParamDTO> findAll() {
        log.debug("Request to get all PlateParams");
        return plateParamRepository.findAll().stream()
            .map(plateParamMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<PlateParamDTO> findOne(Long id) {
        log.debug("Request to get PlateParam : {}", id);
        return plateParamRepository.findById(id)
            .map(plateParamMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PlateParam : {}", id);
        plateParamRepository.deleteById(id);
    }
}
