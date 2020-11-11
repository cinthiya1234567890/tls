package com.neemshade.matri.service.impl;

import com.neemshade.matri.service.QueryPlateService;
import com.neemshade.matri.domain.QueryPlate;
import com.neemshade.matri.repository.QueryPlateRepository;
import com.neemshade.matri.service.dto.QueryPlateDTO;
import com.neemshade.matri.service.mapper.QueryPlateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link QueryPlate}.
 */
@Service
@Transactional
public class QueryPlateServiceImpl implements QueryPlateService {

    private final Logger log = LoggerFactory.getLogger(QueryPlateServiceImpl.class);

    private final QueryPlateRepository queryPlateRepository;

    private final QueryPlateMapper queryPlateMapper;

    public QueryPlateServiceImpl(QueryPlateRepository queryPlateRepository, QueryPlateMapper queryPlateMapper) {
        this.queryPlateRepository = queryPlateRepository;
        this.queryPlateMapper = queryPlateMapper;
    }

    @Override
    public QueryPlateDTO save(QueryPlateDTO queryPlateDTO) {
        log.debug("Request to save QueryPlate : {}", queryPlateDTO);
        QueryPlate queryPlate = queryPlateMapper.toEntity(queryPlateDTO);
        queryPlate = queryPlateRepository.save(queryPlate);
        return queryPlateMapper.toDto(queryPlate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<QueryPlateDTO> findAll() {
        log.debug("Request to get all QueryPlates");
        return queryPlateRepository.findAll().stream()
            .map(queryPlateMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<QueryPlateDTO> findOne(Long id) {
        log.debug("Request to get QueryPlate : {}", id);
        return queryPlateRepository.findById(id)
            .map(queryPlateMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete QueryPlate : {}", id);
        queryPlateRepository.deleteById(id);
    }
}
