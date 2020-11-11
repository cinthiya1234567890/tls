package com.neemshade.matri.service.impl;

import com.neemshade.matri.service.IssueService;
import com.neemshade.matri.domain.Issue;
import com.neemshade.matri.repository.IssueRepository;
import com.neemshade.matri.service.dto.IssueDTO;
import com.neemshade.matri.service.mapper.IssueMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Issue}.
 */
@Service
@Transactional
public class IssueServiceImpl implements IssueService {

    private final Logger log = LoggerFactory.getLogger(IssueServiceImpl.class);

    private final IssueRepository issueRepository;

    private final IssueMapper issueMapper;

    public IssueServiceImpl(IssueRepository issueRepository, IssueMapper issueMapper) {
        this.issueRepository = issueRepository;
        this.issueMapper = issueMapper;
    }

    @Override
    public IssueDTO save(IssueDTO issueDTO) {
        log.debug("Request to save Issue : {}", issueDTO);
        Issue issue = issueMapper.toEntity(issueDTO);
        issue = issueRepository.save(issue);
        return issueMapper.toDto(issue);
    }

    @Override
    @Transactional(readOnly = true)
    public List<IssueDTO> findAll() {
        log.debug("Request to get all Issues");
        return issueRepository.findAll().stream()
            .map(issueMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<IssueDTO> findOne(Long id) {
        log.debug("Request to get Issue : {}", id);
        return issueRepository.findById(id)
            .map(issueMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Issue : {}", id);
        issueRepository.deleteById(id);
    }
}
