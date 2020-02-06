package com.pms.controller;

import com.pms.model.staff.Receptionist;
import com.pms.model.staff.Staff;
import com.pms.service.ReceptionistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * The type Receptionist controller.
 */
@RestController
@RequestMapping("api/receptionists")
@CrossOrigin
public class ReceptionistController {

    private final ReceptionistService receptionistService;

    /**
     * Instantiates a new Receptionist controller.
     *
     * @param receptionistService the receptionist service
     */
    @Autowired
    public ReceptionistController(ReceptionistService receptionistService) {
        this.receptionistService = receptionistService;
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    @GetMapping
    public Iterable<Receptionist> getAll() {
        return receptionistService.getAll();
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    @GetMapping(path = "{id}")
    public Receptionist getById(@PathVariable Long id) {
        return receptionistService.getById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Could not find receptionist with id: " + id));
    }

    /**
     * Gets by username.
     *
     * @param username the username
     * @return the by username
     */
    @GetMapping(path = "username/{username}")
    public Receptionist getByUsername(@PathVariable String username) {
        return receptionistService.getByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Staff not found"));
    }

    /**
     * Create receptionist.
     *
     * @param receptionist the receptionist
     * @return the receptionist
     */
    @PostMapping
    public Receptionist create(@RequestBody Receptionist receptionist) {
        return receptionistService.create(receptionist)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Error creating receptionist"));
    }


    /**
     * Update receptionist.
     *
     * @param receptionist the receptionist
     * @param id           the id
     * @return the receptionist
     */
    @PutMapping(path = "{id}")
    public Receptionist update(
            @RequestBody Receptionist receptionist, @PathVariable Long id) {
        return receptionistService.update(receptionist, id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Error updating receptionist"));
    }

    /**
     * Delete response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        receptionistService.delete(id);
        return ResponseEntity.ok()
                .body("Receptionist with id: " + id + " deleted");
    }
}
