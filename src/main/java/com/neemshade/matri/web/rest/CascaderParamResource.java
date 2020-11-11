package com.neemshade.matri.web.rest;

import com.neemshade.matri.service.CascaderParamService;
import com.neemshade.matri.web.rest.errors.BadRequestAlertException;
import com.neemshade.matri.service.dto.CascaderParamDTO;

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
 * REST controller for managing {@link com.neemshade.matri.domain.CascaderParam}.
 */
@RestController
@RequestMapping("/api")
public class CascaderParamResource {

    private final Logger log = LoggerFactory.getLogger(CascaderParamResource.class);

    private static final String ENTITY_NAME = "cascaderParam";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CascaderParamService cascaderParamService;

    public CascaderParamResource(CascaderParamService cascaderParamService) {
        this.cascaderParamService = cascaderParamService;
    }

    /**
     * {@code POST  /cascader-params} : Create a new cascaderParam.
     *
     * @param cascaderParamDTO the cascaderParamDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cascaderParamDTO, or with status {@code 400 (Bad Request)} if the cascaderParam has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cascader-params")
    public ResponseEntity<CascaderParamDTO> createCascaderParam(@RequestBody CascaderParamDTO cascaderParamDTO) throws URISyntaxException {
        log.debug("REST request to save CascaderParam : {}", cascaderParamDTO);
        if (cascaderParamDTO.getId() != null) {
            throw new BadRequestAlertException("A new cascaderParam cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CascaderParamDTO result = cascaderParamService.save(cascaderParamDTO);
        return ResponseEntity.created(new URI("/api/cascader-params/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cascader-params} : Updates an existing cascaderParam.
     *
     * @param cascaderParamDTO the cascaderParamDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cascaderParamDTO,
     * or with status {@code 400 (Bad Request)} if the cascaderParamDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cascaderParamDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cascader-params")
    public ResponseEntity<CascaderParamDTO> updateCascaderParam(@RequestBody CascaderParamDTO cascaderParamDTO) throws URISyntaxException {
        log.debug("REST request to update CascaderParam : {}", cascaderParamDTO);
        if (cascaderParamDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CascaderParamDTO result = cascaderParamService.save(cascaderParamDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, cascaderParamDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cascader-params} : get all the cascaderParams.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cascaderParams in body.
     */
    @GetMapping("/cascader-params")
    public List<CascaderParamDTO> getAllCascaderParams() {
        log.debug("REST request to get all CascaderParams");
        return cascaderParamService.findAll();
    }

    /**
     * {@code GET  /cascader-params/:id} : get the "id" cascaderParam.
     *
     * @param id the id of the cascaderParamDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cascaderParamDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cascader-params/{id}")
    public ResponseEntity<CascaderParamDTO> getCascaderParam(@PathVariable Long id) {
        log.debug("REST request to get CascaderParam : {}", id);
        Optional<CascaderParamDTO> cascaderParamDTO = cascaderParamService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cascaderParamDTO);
    }

    /**
     * {@code DELETE  /cascader-params/:id} : delete the "id" cascaderParam.
     *
     * @param id the id of the cascaderParamDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cascader-params/{id}")
    public ResponseEntity<Void> deleteCascaderParam(@PathVariable Long id) {
        log.debug("REST request to delete CascaderParam : {}", id);
        cascaderParamService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
