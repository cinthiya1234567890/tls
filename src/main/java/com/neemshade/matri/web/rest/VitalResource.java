package com.neemshade.matri.web.rest;

import com.neemshade.matri.service.VitalService;
import com.neemshade.matri.web.rest.errors.BadRequestAlertException;
import com.neemshade.matri.service.dto.VitalDTO;

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
 * REST controller for managing {@link com.neemshade.matri.domain.Vital}.
 */
@RestController
@RequestMapping("/api")
public class VitalResource {

    private final Logger log = LoggerFactory.getLogger(VitalResource.class);

    private static final String ENTITY_NAME = "vital";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VitalService vitalService;

    public VitalResource(VitalService vitalService) {
        this.vitalService = vitalService;
    }

    /**
     * {@code POST  /vitals} : Create a new vital.
     *
     * @param vitalDTO the vitalDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vitalDTO, or with status {@code 400 (Bad Request)} if the vital has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/vitals")
    public ResponseEntity<VitalDTO> createVital(@RequestBody VitalDTO vitalDTO) throws URISyntaxException {
        log.debug("REST request to save Vital : {}", vitalDTO);
        if (vitalDTO.getId() != null) {
            throw new BadRequestAlertException("A new vital cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VitalDTO result = vitalService.save(vitalDTO);
        return ResponseEntity.created(new URI("/api/vitals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /vitals} : Updates an existing vital.
     *
     * @param vitalDTO the vitalDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vitalDTO,
     * or with status {@code 400 (Bad Request)} if the vitalDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vitalDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/vitals")
    public ResponseEntity<VitalDTO> updateVital(@RequestBody VitalDTO vitalDTO) throws URISyntaxException {
        log.debug("REST request to update Vital : {}", vitalDTO);
        if (vitalDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VitalDTO result = vitalService.save(vitalDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, vitalDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /vitals} : get all the vitals.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vitals in body.
     */
    @GetMapping("/vitals")
    public List<VitalDTO> getAllVitals() {
        log.debug("REST request to get all Vitals");
        return vitalService.findAll();
    }

    /**
     * {@code GET  /vitals/:id} : get the "id" vital.
     *
     * @param id the id of the vitalDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vitalDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/vitals/{id}")
    public ResponseEntity<VitalDTO> getVital(@PathVariable Long id) {
        log.debug("REST request to get Vital : {}", id);
        Optional<VitalDTO> vitalDTO = vitalService.findOne(id);
        return ResponseUtil.wrapOrNotFound(vitalDTO);
    }

    /**
     * {@code DELETE  /vitals/:id} : delete the "id" vital.
     *
     * @param id the id of the vitalDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/vitals/{id}")
    public ResponseEntity<Void> deleteVital(@PathVariable Long id) {
        log.debug("REST request to delete Vital : {}", id);
        vitalService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
