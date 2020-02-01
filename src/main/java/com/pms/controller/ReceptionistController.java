package com.pms.controller;

import com.pms.model.staff.Receptionist;
import com.pms.service.ReceptionistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/receptionists")
@CrossOrigin
public class ReceptionistController {

    private final ReceptionistService receptionistService;

    @Autowired
    public ReceptionistController(ReceptionistService receptionistService) {
        this.receptionistService = receptionistService;
    }

    @GetMapping
    public Iterable<Receptionist> getAll() {
        return receptionistService.getAll();
    }

    @GetMapping(path = "{id}")
    public Receptionist getById(@PathVariable Long id) {
        return receptionistService.getById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Could not find receptionist with id: " + id));
    }

    @PostMapping
    public Receptionist create(@RequestBody Receptionist receptionist) {
        return receptionistService.create(receptionist)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Error creating receptionist"));
    }

    @PutMapping(path = "{id}")
    public Receptionist update(
            @RequestBody Receptionist receptionist, @PathVariable Long id) {
        return receptionistService.update(receptionist, id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Error updating receptionist"));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        receptionistService.delete(id);
        return ResponseEntity.ok()
                .body("Receptionist with id: " + id + " deleted");
    }
}
