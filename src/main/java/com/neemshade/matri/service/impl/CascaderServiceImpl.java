package com.neemshade.matri.service.impl;

import com.neemshade.matri.service.CascaderService;
import com.neemshade.matri.domain.Cascader;
import com.neemshade.matri.repository.CascaderRepository;
import com.neemshade.matri.service.dto.CascaderDTO;
import com.neemshade.matri.service.mapper.CascaderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Cascader}.
 */
@Service
@Transactional
public class CascaderServiceImpl implements CascaderService {

    private final Logger log = LoggerFactory.getLogger(CascaderServiceImpl.class);

    private final CascaderRepository cascaderRepository;

    private final CascaderMapper cascaderMapper;

    public CascaderServiceImpl(CascaderRepository cascaderRepository, CascaderMapper cascaderMapper) {
        this.cascaderRepository = cascaderRepository;
        this.cascaderMapper = cascaderMapper;
    }

    @Override
    public CascaderDTO save(CascaderDTO cascaderDTO) {
        log.debug("Request to save Cascader : {}", cascaderDTO);
        Cascader cascader = cascaderMapper.toEntity(cascaderDTO);
        cascader = cascaderRepository.save(cascader);
        return cascaderMapper.toDto(cascader);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CascaderDTO> findAll() {
        log.debug("Request to get all Cascaders");
        return cascaderRepository.findAll().stream()
            .map(cascaderMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<CascaderDTO> findOne(Long id) {
        log.debug("Request to get Cascader : {}", id);
        return cascaderRepository.findById(id)
            .map(cascaderMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Cascader : {}", id);
        cascaderRepository.deleteById(id);
    }

	@Override
	public Optional<CascaderDTO> findOneByCascaderName(String cascaderName) {
		log.debug("Request to get Cascader by name : {}", cascaderName);
        return cascaderRepository.findTopByCascaderName(cascaderName)
            .map(cascaderMapper::toDto);
	}

	@Override
	public Map<String, Cascader> findAllCascaders() {
		Map<String, Cascader> cascaderMap = new HashMap<>();
		log.debug("Request to get all Cascader");
		List<Cascader> cascaderList = cascaderRepository.findAll();
		
		for (Cascader cascader : cascaderList) {
			cascaderMap.put(cascader.getCascaderName(), cascader);
		}
		
		
		return cascaderMap;
	}
}
