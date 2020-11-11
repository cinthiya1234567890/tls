package com.neemshade.matri.web.rest;

import com.neemshade.matri.service.PlateParamService;
import com.neemshade.matri.web.rest.errors.BadRequestAlertException;
import com.neemshade.matri.service.dto.PlateParamDTO;

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
 * REST controller for managing {@link com.neemshade.matri.domain.PlateParam}.
 */
@RestController
@RequestMapping("/api")
public class PlateParamResource {

    private final Logger log = LoggerFactory.getLogger(PlateParamResource.class);

    private static final String ENTITY_NAME = "plateParam";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PlateParamService plateParamService;

    public PlateParamResource(PlateParamService plateParamService) {
        this.plateParamService = plateParamService;
    }

    /**
     * {@code POST  /plate-params} : Create a new plateParam.
     *
     * @param plateParamDTO the plateParamDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new plateParamDTO, or with status {@code 400 (Bad Request)} if the plateParam has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/plate-params")
    public ResponseEntity<PlateParamDTO> createPlateParam(@RequestBody PlateParamDTO plateParamDTO) throws URISyntaxException {
        log.debug("REST request to save PlateParam : {}", plateParamDTO);
        if (plateParamDTO.getId() != null) {
            throw new BadRequestAlertException("A new plateParam cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlateParamDTO result = plateParamService.save(plateParamDTO);
        return ResponseEntity.created(new URI("/api/plate-params/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /plate-params} : Updates an existing plateParam.
     *
     * @param plateParamDTO the plateParamDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated plateParamDTO,
     * or with status {@code 400 (Bad Request)} if the plateParamDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the plateParamDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/plate-params")
    public ResponseEntity<PlateParamDTO> updatePlateParam(@RequestBody PlateParamDTO plateParamDTO) throws URISyntaxException {
        log.debug("REST request to update PlateParam : {}", plateParamDTO);
        if (plateParamDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlateParamDTO result = plateParamService.save(plateParamDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, plateParamDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /plate-params} : get all the plateParams.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of plateParams in body.
     */
    @GetMapping("/plate-params")
    public List<PlateParamDTO> getAllPlateParams() {
        log.debug("REST request to get all PlateParams");
        return plateParamService.findAll();
    }

    /**
     * {@code GET  /plate-params/:id} : get the "id" plateParam.
     *
     * @param id the id of the plateParamDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the plateParamDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/plate-params/{id}")
    public ResponseEntity<PlateParamDTO> getPlateParam(@PathVariable Long id) {
        log.debug("REST request to get PlateParam : {}", id);
        Optional<PlateParamDTO> plateParamDTO = plateParamService.findOne(id);
        return ResponseUtil.wrapOrNotFound(plateParamDTO);
    }

    /**
     * {@code DELETE  /plate-params/:id} : delete the "id" plateParam.
     *
     * @param id the id of the plateParamDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/plate-params/{id}")
    public ResponseEntity<Void> deletePlateParam(@PathVariable Long id) {
        log.debug("REST request to delete PlateParam : {}", id);
        plateParamService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
