package com.pms.controller;

import com.pms.model.staff.PaymentStaff;
import com.pms.model.staff.Staff;
import com.pms.service.PaymentStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * The type Payment staff controller.
 */
@RestController
@RequestMapping("api/payment-staff")
@CrossOrigin
public class PaymentStaffController {

    private final PaymentStaffService paymentStaffService;

    /**
     * Instantiates a new Payment staff controller.
     *
     * @param paymentStaffService the payment staff service
     */
    @Autowired
    public PaymentStaffController(PaymentStaffService paymentStaffService) {
        this.paymentStaffService = paymentStaffService;
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    @GetMapping
    public Iterable<PaymentStaff> getAll() {
        return paymentStaffService.getAll();
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    @GetMapping(path = "{id}")
    public PaymentStaff getById(@PathVariable Long id) {
        return paymentStaffService.getById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Could not find payment staff with id: " + id));
    }

    /**
     * Create payment staff.
     *
     * @param staff the staff
     * @return the payment staff
     */
    @PostMapping
    public PaymentStaff create(@RequestBody PaymentStaff staff) {
        return paymentStaffService.create(staff)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Error creating payment staff"));
    }

    /**
     * Gets by username.
     *
     * @param username the username
     * @return the by username
     */
    @GetMapping(path = "username/{username}")
    public PaymentStaff getByUsername(@PathVariable String username) {
        return paymentStaffService.getByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Staff not found"));
    }

    /**
     * Update payment staff.
     *
     * @param staff the staff
     * @param id    the id
     * @return the payment staff
     */
    @PutMapping(path = "{id}")
    public PaymentStaff update(
            @RequestBody PaymentStaff staff, @PathVariable Long id) {
        return paymentStaffService.update(staff, id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Error updating payment staff"));
    }

    /**
     * Delete response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        paymentStaffService.delete(id);
        return ResponseEntity.ok()
                .body("Payment staff with id: " + id + " deleted");
    }
}
