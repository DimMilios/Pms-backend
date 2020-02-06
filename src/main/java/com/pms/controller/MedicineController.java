package com.pms.controller;

import com.pms.dao.MedicineDao;
import com.pms.model.prescription.Medicine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * The type Medicine controller.
 */
@RestController
@RequestMapping("api/medicine")
@CrossOrigin
public class MedicineController {

    private final MedicineDao medicineDao;

    /**
     * Instantiates a new Medicine controller.
     *
     * @param medicineDao the medicine dao
     */
    @Autowired
    public MedicineController(MedicineDao medicineDao) {
        this.medicineDao = medicineDao;
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    @GetMapping
    public Iterable<Medicine> getAll() {
        return medicineDao.findAll();
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    @GetMapping(path = "{id}")
    public Medicine getById(@PathVariable Long id) {
        return medicineDao.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                   HttpStatus.NOT_FOUND, "Could not find medicine with id: " + id
                ));
    }

    /**
     * Create response entity.
     *
     * @param medicine the medicine
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity<Medicine> create(@RequestBody Medicine medicine) {
        Medicine med = medicineDao.save(medicine);

        return ResponseEntity.ok().body(med);
    }

    /**
     * Update response entity.
     *
     * @param medicineToUpdate the medicine to update
     * @param id               the id
     * @return the response entity
     */
    @PutMapping(path = "{id}")
    public ResponseEntity<Medicine> update(@RequestBody Medicine medicineToUpdate,
                           @PathVariable Long id) {
        Medicine med = medicineDao.findById(id)
                .map(medicine -> {
                    medicine.setCost(medicineToUpdate.getCost());
                    medicine.setName(medicineToUpdate.getName());
                    return medicine;
                })
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Could not find medicine with id: " + id
                ));

        return ResponseEntity.ok().body(medicineDao.save(med));
    }

    /**
     * Delete response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        medicineDao.deleteById(id);

        return ResponseEntity.ok().body("Medicine with id: " + id + " deleted");
    }
}
