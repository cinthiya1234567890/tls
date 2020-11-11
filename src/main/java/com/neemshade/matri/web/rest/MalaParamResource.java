package com.neemshade.matri.web.rest;

import com.neemshade.matri.service.MalaParamService;
import com.neemshade.matri.web.rest.errors.BadRequestAlertException;
import com.neemshade.matri.service.dto.MalaParamDTO;

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
 * REST controller for managing {@link com.neemshade.matri.domain.MalaParam}.
 */
@RestController
@RequestMapping("/api")
public class MalaParamResource {

    private final Logger log = LoggerFactory.getLogger(MalaParamResource.class);

    private static final String ENTITY_NAME = "malaParam";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MalaParamService malaParamService;

    public MalaParamResource(MalaParamService malaParamService) {
        this.malaParamService = malaParamService;
    }

    /**
     * {@code POST  /mala-params} : Create a new malaParam.
     *
     * @param malaParamDTO the malaParamDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new malaParamDTO, or with status {@code 400 (Bad Request)} if the malaParam has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/mala-params")
    public ResponseEntity<MalaParamDTO> createMalaParam(@RequestBody MalaParamDTO malaParamDTO) throws URISyntaxException {
        log.debug("REST request to save MalaParam : {}", malaParamDTO);
        if (malaParamDTO.getId() != null) {
            throw new BadRequestAlertException("A new malaParam cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MalaParamDTO result = malaParamService.save(malaParamDTO);
        return ResponseEntity.created(new URI("/api/mala-params/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /mala-params} : Updates an existing malaParam.
     *
     * @param malaParamDTO the malaParamDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated malaParamDTO,
     * or with status {@code 400 (Bad Request)} if the malaParamDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the malaParamDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/mala-params")
    public ResponseEntity<MalaParamDTO> updateMalaParam(@RequestBody MalaParamDTO malaParamDTO) throws URISyntaxException {
        log.debug("REST request to update MalaParam : {}", malaParamDTO);
        if (malaParamDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MalaParamDTO result = malaParamService.save(malaParamDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, malaParamDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /mala-params} : get all the malaParams.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of malaParams in body.
     */
    @GetMapping("/mala-params")
    public List<MalaParamDTO> getAllMalaParams() {
        log.debug("REST request to get all MalaParams");
        return malaParamService.findAll();
    }

    /**
     * {@code GET  /mala-params/:id} : get the "id" malaParam.
     *
     * @param id the id of the malaParamDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the malaParamDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mala-params/{id}")
    public ResponseEntity<MalaParamDTO> getMalaParam(@PathVariable Long id) {
        log.debug("REST request to get MalaParam : {}", id);
        Optional<MalaParamDTO> malaParamDTO = malaParamService.findOne(id);
        return ResponseUtil.wrapOrNotFound(malaParamDTO);
    }

    /**
     * {@code DELETE  /mala-params/:id} : delete the "id" malaParam.
     *
     * @param id the id of the malaParamDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/mala-params/{id}")
    public ResponseEntity<Void> deleteMalaParam(@PathVariable Long id) {
        log.debug("REST request to delete MalaParam : {}", id);
        malaParamService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
