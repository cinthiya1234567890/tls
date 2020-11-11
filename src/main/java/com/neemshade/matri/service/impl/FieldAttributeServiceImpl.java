package com.neemshade.matri.service.impl;

import com.neemshade.matri.service.FieldAttributeService;
import com.neemshade.matri.domain.FieldAttribute;
import com.neemshade.matri.repository.FieldAttributeRepository;
import com.neemshade.matri.service.dto.FieldAttributeDTO;
import com.neemshade.matri.service.mapper.FieldAttributeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link FieldAttribute}.
 */
@Service
@Transactional
public class FieldAttributeServiceImpl implements FieldAttributeService {

    private final Logger log = LoggerFactory.getLogger(FieldAttributeServiceImpl.class);

    private final FieldAttributeRepository fieldAttributeRepository;

    private final FieldAttributeMapper fieldAttributeMapper;

    public FieldAttributeServiceImpl(FieldAttributeRepository fieldAttributeRepository, FieldAttributeMapper fieldAttributeMapper) {
        this.fieldAttributeRepository = fieldAttributeRepository;
        this.fieldAttributeMapper = fieldAttributeMapper;
    }

    @Override
    public FieldAttributeDTO save(FieldAttributeDTO fieldAttributeDTO) {
        log.debug("Request to save FieldAttribute : {}", fieldAttributeDTO);
        FieldAttribute fieldAttribute = fieldAttributeMapper.toEntity(fieldAttributeDTO);
        fieldAttribute = fieldAttributeRepository.save(fieldAttribute);
        return fieldAttributeMapper.toDto(fieldAttribute);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FieldAttributeDTO> findAll() {
        log.debug("Request to get all FieldAttributes");
        return fieldAttributeRepository.findAll().stream()
            .map(fieldAttributeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<FieldAttributeDTO> findOne(Long id) {
        log.debug("Request to get FieldAttribute : {}", id);
        return fieldAttributeRepository.findById(id)
            .map(fieldAttributeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete FieldAttribute : {}", id);
        fieldAttributeRepository.deleteById(id);
    }

	@Override
	public void deleteUnderField(Long fieldId) {
		log.debug("Request to delete FieldAttributes under field : {}", fieldId);
        fieldAttributeRepository.deleteByFieldId(fieldId);
	}

	@Override
	public Optional<FieldAttributeDTO> findUniqueFieldAttribute(Long fieldId, String attributeName) {
		log.debug("Request to get unique FieldAttribute : field {}, attributeName {}", fieldId, attributeName);
        return fieldAttributeRepository.findTopByFieldIdAndAttributeName(fieldId, attributeName)
            .map(fieldAttributeMapper::toDto);
	}
}
