package com.neemshade.matri.service.impl;

import com.neemshade.matri.service.QueryService;
import com.neemshade.matri.domain.Query;
import com.neemshade.matri.repository.QueryRepository;
import com.neemshade.matri.service.dto.QueryDTO;
import com.neemshade.matri.service.mapper.QueryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Query}.
 */
@Service
@Transactional
public class QueryServiceImpl implements QueryService {

    private final Logger log = LoggerFactory.getLogger(QueryServiceImpl.class);

    private final QueryRepository queryRepository;

    private final QueryMapper queryMapper;

    public QueryServiceImpl(QueryRepository queryRepository, QueryMapper queryMapper) {
        this.queryRepository = queryRepository;
        this.queryMapper = queryMapper;
    }

    @Override
    public QueryDTO save(QueryDTO queryDTO) {
        log.debug("Request to save Query : {}", queryDTO);
        Query query = queryMapper.toEntity(queryDTO);
        query = queryRepository.save(query);
        return queryMapper.toDto(query);
    }

    @Override
    @Transactional(readOnly = true)
    public List<QueryDTO> findAll() {
        log.debug("Request to get all Queries");
        return queryRepository.findAll().stream()
            .map(queryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<QueryDTO> findOne(Long id) {
        log.debug("Request to get Query : {}", id);
        return queryRepository.findById(id)
            .map(queryMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Query : {}", id);
        queryRepository.deleteById(id);
    }
}
