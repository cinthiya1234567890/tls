package com.neemshade.matri.web.rest;

import com.neemshade.matri.service.QueryPlateService;
import com.neemshade.matri.web.rest.errors.BadRequestAlertException;
import com.neemshade.matri.service.dto.QueryPlateDTO;

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
 * REST controller for managing {@link com.neemshade.matri.domain.QueryPlate}.
 */
@RestController
@RequestMapping("/api")
public class QueryPlateResource {

    private final Logger log = LoggerFactory.getLogger(QueryPlateResource.class);

    private static final String ENTITY_NAME = "queryPlate";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QueryPlateService queryPlateService;

    public QueryPlateResource(QueryPlateService queryPlateService) {
        this.queryPlateService = queryPlateService;
    }

    /**
     * {@code POST  /query-plates} : Create a new queryPlate.
     *
     * @param queryPlateDTO the queryPlateDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new queryPlateDTO, or with status {@code 400 (Bad Request)} if the queryPlate has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/query-plates")
    public ResponseEntity<QueryPlateDTO> createQueryPlate(@RequestBody QueryPlateDTO queryPlateDTO) throws URISyntaxException {
        log.debug("REST request to save QueryPlate : {}", queryPlateDTO);
        if (queryPlateDTO.getId() != null) {
            throw new BadRequestAlertException("A new queryPlate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QueryPlateDTO result = queryPlateService.save(queryPlateDTO);
        return ResponseEntity.created(new URI("/api/query-plates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /query-plates} : Updates an existing queryPlate.
     *
     * @param queryPlateDTO the queryPlateDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated queryPlateDTO,
     * or with status {@code 400 (Bad Request)} if the queryPlateDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the queryPlateDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/query-plates")
    public ResponseEntity<QueryPlateDTO> updateQueryPlate(@RequestBody QueryPlateDTO queryPlateDTO) throws URISyntaxException {
        log.debug("REST request to update QueryPlate : {}", queryPlateDTO);
        if (queryPlateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QueryPlateDTO result = queryPlateService.save(queryPlateDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, queryPlateDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /query-plates} : get all the queryPlates.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of queryPlates in body.
     */
    @GetMapping("/query-plates")
    public List<QueryPlateDTO> getAllQueryPlates() {
        log.debug("REST request to get all QueryPlates");
        return queryPlateService.findAll();
    }

    /**
     * {@code GET  /query-plates/:id} : get the "id" queryPlate.
     *
     * @param id the id of the queryPlateDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the queryPlateDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/query-plates/{id}")
    public ResponseEntity<QueryPlateDTO> getQueryPlate(@PathVariable Long id) {
        log.debug("REST request to get QueryPlate : {}", id);
        Optional<QueryPlateDTO> queryPlateDTO = queryPlateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(queryPlateDTO);
    }

    /**
     * {@code DELETE  /query-plates/:id} : delete the "id" queryPlate.
     *
     * @param id the id of the queryPlateDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/query-plates/{id}")
    public ResponseEntity<Void> deleteQueryPlate(@PathVariable Long id) {
        log.debug("REST request to delete QueryPlate : {}", id);
        queryPlateService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
