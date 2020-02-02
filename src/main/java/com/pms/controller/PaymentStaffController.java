package com.pms.controller;

import com.pms.model.staff.PaymentStaff;
import com.pms.service.PaymentStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/payment-staff")
@CrossOrigin
public class PaymentStaffController {

    private final PaymentStaffService paymentStaffService;

    @Autowired
    public PaymentStaffController(PaymentStaffService paymentStaffService) {
        this.paymentStaffService = paymentStaffService;
    }

    @GetMapping
    public Iterable<PaymentStaff> getAll() {
        return paymentStaffService.getAll();
    }

    @GetMapping(path = "{id}")
    public PaymentStaff getById(@PathVariable Long id) {
        return paymentStaffService.getById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Could not find payment staff with id: " + id));
    }

    @PostMapping
    public PaymentStaff create(@RequestBody PaymentStaff staff) {
        return paymentStaffService.create(staff)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Error creating payment staff"));
    }

    @PutMapping(path = "{id}")
    public PaymentStaff update(
            @RequestBody PaymentStaff staff, @PathVariable Long id) {
        return paymentStaffService.update(staff, id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Error updating payment staff"));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        paymentStaffService.delete(id);
        return ResponseEntity.ok()
                .body("Payment staff with id: " + id + " deleted");
    }
}
