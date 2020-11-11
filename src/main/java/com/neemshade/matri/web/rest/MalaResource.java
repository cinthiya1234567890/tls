package com.neemshade.matri.web.rest;

import com.neemshade.matri.service.MalaService;
import com.neemshade.matri.web.rest.errors.BadRequestAlertException;
import com.neemshade.matri.service.dto.MalaDTO;

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
 * REST controller for managing {@link com.neemshade.matri.domain.Mala}.
 */
@RestController
@RequestMapping("/api")
public class MalaResource {

    private final Logger log = LoggerFactory.getLogger(MalaResource.class);

    private static final String ENTITY_NAME = "mala";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MalaService malaService;

    public MalaResource(MalaService malaService) {
        this.malaService = malaService;
    }

    /**
     * {@code POST  /malas} : Create a new mala.
     *
     * @param malaDTO the malaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new malaDTO, or with status {@code 400 (Bad Request)} if the mala has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/malas")
    public ResponseEntity<MalaDTO> createMala(@RequestBody MalaDTO malaDTO) throws URISyntaxException {
        log.debug("REST request to save Mala : {}", malaDTO);
        if (malaDTO.getId() != null) {
            throw new BadRequestAlertException("A new mala cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MalaDTO result = malaService.save(malaDTO);
        return ResponseEntity.created(new URI("/api/malas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /malas} : Updates an existing mala.
     *
     * @param malaDTO the malaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated malaDTO,
     * or with status {@code 400 (Bad Request)} if the malaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the malaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/malas")
    public ResponseEntity<MalaDTO> updateMala(@RequestBody MalaDTO malaDTO) throws URISyntaxException {
        log.debug("REST request to update Mala : {}", malaDTO);
        if (malaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MalaDTO result = malaService.save(malaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, malaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /malas} : get all the malas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of malas in body.
     */
    @GetMapping("/malas")
    public List<MalaDTO> getAllMalas() {
        log.debug("REST request to get all Malas");
        return malaService.findAll();
    }

    /**
     * {@code GET  /malas/:id} : get the "id" mala.
     *
     * @param id the id of the malaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the malaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/malas/{id}")
    public ResponseEntity<MalaDTO> getMala(@PathVariable Long id) {
        log.debug("REST request to get Mala : {}", id);
        Optional<MalaDTO> malaDTO = malaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(malaDTO);
    }

    /**
     * {@code DELETE  /malas/:id} : delete the "id" mala.
     *
     * @param id the id of the malaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/malas/{id}")
    public ResponseEntity<Void> deleteMala(@PathVariable Long id) {
        log.debug("REST request to delete Mala : {}", id);
        malaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
