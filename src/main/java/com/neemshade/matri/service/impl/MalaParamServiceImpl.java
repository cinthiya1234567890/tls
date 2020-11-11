package com.neemshade.matri.service.impl;

import com.neemshade.matri.service.MalaParamService;
import com.neemshade.matri.domain.MalaParam;
import com.neemshade.matri.repository.MalaParamRepository;
import com.neemshade.matri.service.dto.MalaParamDTO;
import com.neemshade.matri.service.mapper.MalaParamMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link MalaParam}.
 */
@Service
@Transactional
public class MalaParamServiceImpl implements MalaParamService {

    private final Logger log = LoggerFactory.getLogger(MalaParamServiceImpl.class);

    private final MalaParamRepository malaParamRepository;

    private final MalaParamMapper malaParamMapper;

    public MalaParamServiceImpl(MalaParamRepository malaParamRepository, MalaParamMapper malaParamMapper) {
        this.malaParamRepository = malaParamRepository;
        this.malaParamMapper = malaParamMapper;
    }

    @Override
    public MalaParamDTO save(MalaParamDTO malaParamDTO) {
        log.debug("Request to save MalaParam : {}", malaParamDTO);
        MalaParam malaParam = malaParamMapper.toEntity(malaParamDTO);
        malaParam = malaParamRepository.save(malaParam);
        return malaParamMapper.toDto(malaParam);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MalaParamDTO> findAll() {
        log.debug("Request to get all MalaParams");
        return malaParamRepository.findAll().stream()
            .map(malaParamMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<MalaParamDTO> findOne(Long id) {
        log.debug("Request to get MalaParam : {}", id);
        return malaParamRepository.findById(id)
            .map(malaParamMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete MalaParam : {}", id);
        malaParamRepository.deleteById(id);
    }

	@Override
	public Optional<MalaParamDTO> findMaxPeckOrderUnderMala(Long malaId) {
		log.debug("Request to get MalaParam with max peckOrder : {}", malaId);
        return malaParamRepository.findTopByMalaIdOrderByPeckOrderDesc(malaId)
            .map(malaParamMapper::toDto);
	}
}
