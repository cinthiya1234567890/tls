package com.neemshade.matri.service.impl;

import com.neemshade.matri.service.FieldService;
import com.neemshade.matri.domain.Field;
import com.neemshade.matri.repository.FieldRepository;
import com.neemshade.matri.service.dto.FieldDTO;
import com.neemshade.matri.service.mapper.FieldMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Field}.
 */
@Service
@Transactional
public class FieldServiceImpl implements FieldService {

    private final Logger log = LoggerFactory.getLogger(FieldServiceImpl.class);

    private final FieldRepository fieldRepository;

    private final FieldMapper fieldMapper;

    public FieldServiceImpl(FieldRepository fieldRepository, FieldMapper fieldMapper) {
        this.fieldRepository = fieldRepository;
        this.fieldMapper = fieldMapper;
    }

    @Override
    public FieldDTO save(FieldDTO fieldDTO) {
        log.debug("Request to save Field : {}", fieldDTO);
        Field field = fieldMapper.toEntity(fieldDTO);
        field = fieldRepository.save(field);
        return fieldMapper.toDto(field);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FieldDTO> findAll() {
        log.debug("Request to get all Fields");
        return fieldRepository.findAll().stream()
            .map(fieldMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<FieldDTO> findOne(Long id) {
        log.debug("Request to get Field : {}", id);
        return fieldRepository.findById(id)
            .map(fieldMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Field : {}", id);
        fieldRepository.deleteById(id);
    }

	@Override
	public Optional<FieldDTO> findMaxPeckOrder() {
		log.debug("Request to get Field with max peckOrder");
        return fieldRepository.findTopByOrderByPeckOrderDesc()
            .map(fieldMapper::toDto);
	}

	@Override
	public Optional<FieldDTO> findUniqueFieldUnderMala(String malaName, String fieldName) {
		log.debug("Request to get unique Field : {}", fieldName);
        return fieldRepository.findTopByMalaParamsMalaMalaNameAndFieldName(malaName, fieldName)
            .map(fieldMapper::toDto);
	}
}
