package com.neemshade.matri.web.rest;

import com.neemshade.matri.service.FieldAttributeService;
import com.neemshade.matri.web.rest.errors.BadRequestAlertException;
import com.neemshade.matri.service.dto.FieldAttributeDTO;

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
 * REST controller for managing {@link com.neemshade.matri.domain.FieldAttribute}.
 */
@RestController
@RequestMapping("/api")
public class FieldAttributeResource {

    private final Logger log = LoggerFactory.getLogger(FieldAttributeResource.class);

    private static final String ENTITY_NAME = "fieldAttribute";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FieldAttributeService fieldAttributeService;

    public FieldAttributeResource(FieldAttributeService fieldAttributeService) {
        this.fieldAttributeService = fieldAttributeService;
    }

    /**
     * {@code POST  /field-attributes} : Create a new fieldAttribute.
     *
     * @param fieldAttributeDTO the fieldAttributeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fieldAttributeDTO, or with status {@code 400 (Bad Request)} if the fieldAttribute has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/field-attributes")
    public ResponseEntity<FieldAttributeDTO> createFieldAttribute(@RequestBody FieldAttributeDTO fieldAttributeDTO) throws URISyntaxException {
        log.debug("REST request to save FieldAttribute : {}", fieldAttributeDTO);
        if (fieldAttributeDTO.getId() != null) {
            throw new BadRequestAlertException("A new fieldAttribute cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FieldAttributeDTO result = fieldAttributeService.save(fieldAttributeDTO);
        return ResponseEntity.created(new URI("/api/field-attributes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /field-attributes} : Updates an existing fieldAttribute.
     *
     * @param fieldAttributeDTO the fieldAttributeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fieldAttributeDTO,
     * or with status {@code 400 (Bad Request)} if the fieldAttributeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fieldAttributeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/field-attributes")
    public ResponseEntity<FieldAttributeDTO> updateFieldAttribute(@RequestBody FieldAttributeDTO fieldAttributeDTO) throws URISyntaxException {
        log.debug("REST request to update FieldAttribute : {}", fieldAttributeDTO);
        if (fieldAttributeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FieldAttributeDTO result = fieldAttributeService.save(fieldAttributeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, fieldAttributeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /field-attributes} : get all the fieldAttributes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fieldAttributes in body.
     */
    @GetMapping("/field-attributes")
    public List<FieldAttributeDTO> getAllFieldAttributes() {
        log.debug("REST request to get all FieldAttributes");
        return fieldAttributeService.findAll();
    }

    /**
     * {@code GET  /field-attributes/:id} : get the "id" fieldAttribute.
     *
     * @param id the id of the fieldAttributeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fieldAttributeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/field-attributes/{id}")
    public ResponseEntity<FieldAttributeDTO> getFieldAttribute(@PathVariable Long id) {
        log.debug("REST request to get FieldAttribute : {}", id);
        Optional<FieldAttributeDTO> fieldAttributeDTO = fieldAttributeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fieldAttributeDTO);
    }

    /**
     * {@code DELETE  /field-attributes/:id} : delete the "id" fieldAttribute.
     *
     * @param id the id of the fieldAttributeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/field-attributes/{id}")
    public ResponseEntity<Void> deleteFieldAttribute(@PathVariable Long id) {
        log.debug("REST request to delete FieldAttribute : {}", id);
        fieldAttributeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
