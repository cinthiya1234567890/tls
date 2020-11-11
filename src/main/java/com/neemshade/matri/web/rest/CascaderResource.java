package com.neemshade.matri.web.rest;

import com.neemshade.matri.service.CascaderService;
import com.neemshade.matri.web.rest.errors.BadRequestAlertException;
import com.neemshade.matri.service.dto.CascaderDTO;

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
 * REST controller for managing {@link com.neemshade.matri.domain.Cascader}.
 */
@RestController
@RequestMapping("/api")
public class CascaderResource {

    private final Logger log = LoggerFactory.getLogger(CascaderResource.class);

    private static final String ENTITY_NAME = "cascader";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CascaderService cascaderService;

    public CascaderResource(CascaderService cascaderService) {
        this.cascaderService = cascaderService;
    }

    /**
     * {@code POST  /cascaders} : Create a new cascader.
     *
     * @param cascaderDTO the cascaderDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cascaderDTO, or with status {@code 400 (Bad Request)} if the cascader has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cascaders")
    public ResponseEntity<CascaderDTO> createCascader(@RequestBody CascaderDTO cascaderDTO) throws URISyntaxException {
        log.debug("REST request to save Cascader : {}", cascaderDTO);
        if (cascaderDTO.getId() != null) {
            throw new BadRequestAlertException("A new cascader cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CascaderDTO result = cascaderService.save(cascaderDTO);
        return ResponseEntity.created(new URI("/api/cascaders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cascaders} : Updates an existing cascader.
     *
     * @param cascaderDTO the cascaderDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cascaderDTO,
     * or with status {@code 400 (Bad Request)} if the cascaderDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cascaderDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cascaders")
    public ResponseEntity<CascaderDTO> updateCascader(@RequestBody CascaderDTO cascaderDTO) throws URISyntaxException {
        log.debug("REST request to update Cascader : {}", cascaderDTO);
        if (cascaderDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CascaderDTO result = cascaderService.save(cascaderDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, cascaderDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cascaders} : get all the cascaders.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cascaders in body.
     */
    @GetMapping("/cascaders")
    public List<CascaderDTO> getAllCascaders() {
        log.debug("REST request to get all Cascaders");
        return cascaderService.findAll();
    }

    /**
     * {@code GET  /cascaders/:id} : get the "id" cascader.
     *
     * @param id the id of the cascaderDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cascaderDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cascaders/{id}")
    public ResponseEntity<CascaderDTO> getCascader(@PathVariable Long id) {
        log.debug("REST request to get Cascader : {}", id);
        Optional<CascaderDTO> cascaderDTO = cascaderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cascaderDTO);
    }

    /**
     * {@code DELETE  /cascaders/:id} : delete the "id" cascader.
     *
     * @param id the id of the cascaderDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cascaders/{id}")
    public ResponseEntity<Void> deleteCascader(@PathVariable Long id) {
        log.debug("REST request to delete Cascader : {}", id);
        cascaderService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
