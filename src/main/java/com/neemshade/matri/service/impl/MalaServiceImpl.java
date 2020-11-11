package com.neemshade.matri.service.impl;

import com.neemshade.matri.service.MalaService;
import com.neemshade.matri.domain.Mala;
import com.neemshade.matri.repository.MalaRepository;
import com.neemshade.matri.service.dto.MalaDTO;
import com.neemshade.matri.service.mapper.MalaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Mala}.
 */
@Service
@Transactional
public class MalaServiceImpl implements MalaService {

    private final Logger log = LoggerFactory.getLogger(MalaServiceImpl.class);

    private final MalaRepository malaRepository;

    private final MalaMapper malaMapper;

    public MalaServiceImpl(MalaRepository malaRepository, MalaMapper malaMapper) {
        this.malaRepository = malaRepository;
        this.malaMapper = malaMapper;
    }

    @Override
    public MalaDTO save(MalaDTO malaDTO) {
        log.debug("Request to save Mala : {}", malaDTO);
        Mala mala = malaMapper.toEntity(malaDTO);
        mala = malaRepository.save(mala);
        return malaMapper.toDto(mala);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MalaDTO> findAll() {
        log.debug("Request to get all Malas");
        return malaRepository.findAll().stream()
            .map(malaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<MalaDTO> findOne(Long id) {
        log.debug("Request to get Mala : {}", id);
        return malaRepository.findById(id)
            .map(malaMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Mala : {}", id);
        malaRepository.deleteById(id);
    }

	@Override
	public Optional<MalaDTO> findUniqueMala(String malaName) {
		log.debug("Request to get unique Mala : {}", malaName);
        return malaRepository.findTopByMalaName(malaName)
            .map(malaMapper::toDto);
	}
}
