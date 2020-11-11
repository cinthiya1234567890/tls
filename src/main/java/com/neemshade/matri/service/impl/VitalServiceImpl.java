package com.neemshade.matri.service.impl;

import com.neemshade.matri.service.VitalService;
import com.neemshade.matri.domain.Vital;
import com.neemshade.matri.repository.VitalRepository;
import com.neemshade.matri.service.dto.VitalDTO;
import com.neemshade.matri.service.mapper.VitalMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Vital}.
 */
@Service
@Transactional
public class VitalServiceImpl implements VitalService {

    private final Logger log = LoggerFactory.getLogger(VitalServiceImpl.class);

    private final VitalRepository vitalRepository;

    private final VitalMapper vitalMapper;

    public VitalServiceImpl(VitalRepository vitalRepository, VitalMapper vitalMapper) {
        this.vitalRepository = vitalRepository;
        this.vitalMapper = vitalMapper;
    }

    @Override
    public VitalDTO save(VitalDTO vitalDTO) {
        log.debug("Request to save Vital : {}", vitalDTO);
        Vital vital = vitalMapper.toEntity(vitalDTO);
        vital = vitalRepository.save(vital);
        return vitalMapper.toDto(vital);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VitalDTO> findAll() {
        log.debug("Request to get all Vitals");
        return vitalRepository.findAll().stream()
            .map(vitalMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<VitalDTO> findOne(Long id) {
        log.debug("Request to get Vital : {}", id);
        return vitalRepository.findById(id)
            .map(vitalMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Vital : {}", id);
        vitalRepository.deleteById(id);
    }
}
