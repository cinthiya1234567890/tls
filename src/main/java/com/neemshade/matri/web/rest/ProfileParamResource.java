package com.neemshade.matri.web.rest;

import com.neemshade.matri.service.ProfileParamService;
import com.neemshade.matri.web.rest.errors.BadRequestAlertException;
import com.neemshade.matri.service.dto.ProfileParamDTO;

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
 * REST controller for managing {@link com.neemshade.matri.domain.ProfileParam}.
 */
@RestController
@RequestMapping("/api")
public class ProfileParamResource {

    private final Logger log = LoggerFactory.getLogger(ProfileParamResource.class);

    private static final String ENTITY_NAME = "profileParam";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProfileParamService profileParamService;

    public ProfileParamResource(ProfileParamService profileParamService) {
        this.profileParamService = profileParamService;
    }

    /**
     * {@code POST  /profile-params} : Create a new profileParam.
     *
     * @param profileParamDTO the profileParamDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new profileParamDTO, or with status {@code 400 (Bad Request)} if the profileParam has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/profile-params")
    public ResponseEntity<ProfileParamDTO> createProfileParam(@RequestBody ProfileParamDTO profileParamDTO) throws URISyntaxException {
        log.debug("REST request to save ProfileParam : {}", profileParamDTO);
        if (profileParamDTO.getId() != null) {
            throw new BadRequestAlertException("A new profileParam cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProfileParamDTO result = profileParamService.save(profileParamDTO);
        return ResponseEntity.created(new URI("/api/profile-params/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /profile-params} : Updates an existing profileParam.
     *
     * @param profileParamDTO the profileParamDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated profileParamDTO,
     * or with status {@code 400 (Bad Request)} if the profileParamDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the profileParamDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/profile-params")
    public ResponseEntity<ProfileParamDTO> updateProfileParam(@RequestBody ProfileParamDTO profileParamDTO) throws URISyntaxException {
        log.debug("REST request to update ProfileParam : {}", profileParamDTO);
        if (profileParamDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProfileParamDTO result = profileParamService.save(profileParamDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, profileParamDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /profile-params} : get all the profileParams.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of profileParams in body.
     */
    @GetMapping("/profile-params")
    public List<ProfileParamDTO> getAllProfileParams() {
        log.debug("REST request to get all ProfileParams");
        return profileParamService.findAll();
    }

    /**
     * {@code GET  /profile-params/:id} : get the "id" profileParam.
     *
     * @param id the id of the profileParamDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the profileParamDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/profile-params/{id}")
    public ResponseEntity<ProfileParamDTO> getProfileParam(@PathVariable Long id) {
        log.debug("REST request to get ProfileParam : {}", id);
        Optional<ProfileParamDTO> profileParamDTO = profileParamService.findOne(id);
        return ResponseUtil.wrapOrNotFound(profileParamDTO);
    }

    /**
     * {@code DELETE  /profile-params/:id} : delete the "id" profileParam.
     *
     * @param id the id of the profileParamDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/profile-params/{id}")
    public ResponseEntity<Void> deleteProfileParam(@PathVariable Long id) {
        log.debug("REST request to delete ProfileParam : {}", id);
        profileParamService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
