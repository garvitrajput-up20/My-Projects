package com.bank.cms.controller;

import com.bank.cms.dto.ErrorResponse;
import com.bank.cms.entity.Customer;
import com.bank.cms.exceptions.CustomException;
import com.bank.cms.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/add-customer")
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
        try {
            customerService.addCustomer(customer);
            String successMessage = "Customer added successfully with ID: " + customer.getCustomerId();
            return ResponseEntity.status(HttpStatus.OK).body(successMessage);
        } catch (Exception customException) {
            log.error("Error adding customer: " + customException);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), customException.getMessage()));
        }
    }

    @GetMapping("/fetch-all")
    public ResponseEntity<?> fetchAllCustomer() {
        List<Customer> customers = customerService.getAllCustomers();

        if (customers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Customers Found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

    @GetMapping("/fetch-by-id")
    public ResponseEntity<?> fetchByCustomerId(@RequestParam String customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        if (customer != null) {
            return ResponseEntity.status(HttpStatus.OK).body(customer);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found with Account ID: " + customerId);
        }
    }

    @PutMapping("/update-customer")
    public ResponseEntity<?> updateCustomer(@RequestParam String customerId, @RequestBody Customer updatedCustomer) {
        try {
            customerService.updateCustomer(customerId, updatedCustomer);
            String successMessage = "Customer updated successfully with ID: " + customerId;
            return ResponseEntity.status(HttpStatus.OK).body(successMessage);
        } catch (CustomException e) {
            return ResponseEntity.status(e.getCode()).body(new ErrorResponse(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error("Error updating customer: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }
    }

    @DeleteMapping("/delete-by-id")
    public ResponseEntity<?> deleteCustomer(@RequestParam String customerId) {
        try {
            boolean isDeleted = customerService.deleteCustomer(customerId);

            if (isDeleted) {
                String successMessage = "Customer deleted having Account ID: " + customerId;
                return ResponseEntity.status(HttpStatus.OK).body(successMessage);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Customer not found with Account ID: " + customerId);
            }
        } catch (CustomException e) {
            return ResponseEntity.status(e.getCode())
                    .body(new ErrorResponse(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }
    }
}