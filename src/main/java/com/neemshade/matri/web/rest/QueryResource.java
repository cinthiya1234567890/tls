package com.neemshade.matri.web.rest;

import com.neemshade.matri.service.QueryService;
import com.neemshade.matri.web.rest.errors.BadRequestAlertException;
import com.neemshade.matri.service.dto.QueryDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.neemshade.matri.domain.Query}.
 */
@RestController
@RequestMapping("/api")
public class QueryResource {

    private final Logger log = LoggerFactory.getLogger(QueryResource.class);

    private static final String ENTITY_NAME = "query";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QueryService queryService;

    public QueryResource(QueryService queryService) {
        this.queryService = queryService;
    }

    /**
     * {@code POST  /queries} : Create a new query.
     *
     * @param queryDTO the queryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new queryDTO, or with status {@code 400 (Bad Request)} if the query has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/queries")
    public ResponseEntity<QueryDTO> createQuery(@RequestBody QueryDTO queryDTO) throws URISyntaxException {
        log.debug("REST request to save Query : {}", queryDTO);
        if (queryDTO.getId() != null) {
            throw new BadRequestAlertException("A new query cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QueryDTO result = queryService.save(queryDTO);
        return ResponseEntity.created(new URI("/api/queries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /queries} : Updates an existing query.
     *
     * @param queryDTO the queryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated queryDTO,
     * or with status {@code 400 (Bad Request)} if the queryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the queryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/queries")
    public ResponseEntity<QueryDTO> updateQuery(@RequestBody QueryDTO queryDTO) throws URISyntaxException {
        log.debug("REST request to update Query : {}", queryDTO);
        if (queryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QueryDTO result = queryService.save(queryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, queryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /queries} : get all the queries.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of queries in body.
     */
    @GetMapping("/queries")
    public List<QueryDTO> getAllQueries() {
        log.debug("REST request to get all Queries");
        return queryService.findAll();
    }

    /**
     * {@code GET  /queries/:id} : get the "id" query.
     *
     * @param id the id of the queryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the queryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/queries/{id}")
    public ResponseEntity<QueryDTO> getQuery(@PathVariable Long id) {
        log.debug("REST request to get Query : {}", id);
        Optional<QueryDTO> queryDTO = queryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(queryDTO);
    }

    /**
     * {@code DELETE  /queries/:id} : delete the "id" query.
     *
     * @param id the id of the queryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/queries/{id}")
    public ResponseEntity<Void> deleteQuery(@PathVariable Long id) {
        log.debug("REST request to delete Query : {}", id);
        queryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
