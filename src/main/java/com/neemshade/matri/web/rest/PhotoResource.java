package com.neemshade.matri.web.rest;

import com.neemshade.matri.service.PhotoService;
import com.neemshade.matri.web.rest.errors.BadRequestAlertException;
import com.neemshade.matri.service.dto.PhotoDTO;

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
 * REST controller for managing {@link com.neemshade.matri.domain.Photo}.
 */
@RestController
@RequestMapping("/api")
public class PhotoResource {

    private final Logger log = LoggerFactory.getLogger(PhotoResource.class);

    private static final String ENTITY_NAME = "photo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PhotoService photoService;

    public PhotoResource(PhotoService photoService) {
        this.photoService = photoService;
    }

    /**
     * {@code POST  /photos} : Create a new photo.
     *
     * @param photoDTO the photoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new photoDTO, or with status {@code 400 (Bad Request)} if the photo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/photos")
    public ResponseEntity<PhotoDTO> createPhoto(@RequestBody PhotoDTO photoDTO) throws URISyntaxException {
        log.debug("REST request to save Photo : {}", photoDTO);
        if (photoDTO.getId() != null) {
            throw new BadRequestAlertException("A new photo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PhotoDTO result = photoService.save(photoDTO);
        return ResponseEntity.created(new URI("/api/photos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /photos} : Updates an existing photo.
     *
     * @param photoDTO the photoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated photoDTO,
     * or with status {@code 400 (Bad Request)} if the photoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the photoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/photos")
    public ResponseEntity<PhotoDTO> updatePhoto(@RequestBody PhotoDTO photoDTO) throws URISyntaxException {
        log.debug("REST request to update Photo : {}", photoDTO);
        if (photoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PhotoDTO result = photoService.save(photoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, photoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /photos} : get all the photos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of photos in body.
     */
    @GetMapping("/photos")
    public List<PhotoDTO> getAllPhotos() {
        log.debug("REST request to get all Photos");
        return photoService.findAll();
    }

    /**
     * {@code GET  /photos/:id} : get the "id" photo.
     *
     * @param id the id of the photoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the photoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/photos/{id}")
    public ResponseEntity<PhotoDTO> getPhoto(@PathVariable Long id) {
        log.debug("REST request to get Photo : {}", id);
        Optional<PhotoDTO> photoDTO = photoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(photoDTO);
    }

    /**
     * {@code DELETE  /photos/:id} : delete the "id" photo.
     *
     * @param id the id of the photoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/photos/{id}")
    public ResponseEntity<Void> deletePhoto(@PathVariable Long id) {
        log.debug("REST request to delete Photo : {}", id);
        photoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
