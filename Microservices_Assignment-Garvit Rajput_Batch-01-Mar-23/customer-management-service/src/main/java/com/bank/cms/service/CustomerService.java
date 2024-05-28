package com.bank.cms.service;

import com.bank.cms.entity.Customer;
import com.bank.cms.exceptions.CustomException;
import com.bank.cms.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public void addCustomer(Customer customer){
        if (isEmailExists(customer.getEmail()) && isEmailExists(customer.getEmail())) {
            throw new CustomException(HttpStatus.BAD_REQUEST.value(), "Email and Mobile already exists");
        }
        if (isMobileNumberExists(customer.getMobile())) {
            throw new CustomException(HttpStatus.BAD_REQUEST.value(), "Mobile number already exists");
        }
        if (isEmailExists(customer.getEmail())) {
            throw new CustomException(HttpStatus.BAD_REQUEST.value(), "Email already exists");
        }

        customerRepository.save(customer);
    }

    public Customer getCustomerById(String customerId){
        return (Customer) customerRepository.findByCustomerId(customerId);
    }

    public List<Customer> getAllCustomers(){
        return (List<Customer>) customerRepository.findAll();
    }

    public boolean deleteCustomer(String customerId) {
        Customer customer = getCustomerById(customerId);

        if (customer != null) {
            customerRepository.delete(customer);
            return true;
        }
        return false;
    }

    public void updateCustomer(String customerId, Customer updatedCustomer) {
        Customer existingCustomer = getCustomerById(customerId);

        if (existingCustomer == null) {
            throw new CustomException(HttpStatus.NOT_FOUND.value(), "Customer not found with ID: " + customerId);
        }

        // Update customer data
        existingCustomer.setName(updatedCustomer.getName());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        existingCustomer.setMobile(updatedCustomer.getMobile());
        existingCustomer.setIsActive(updatedCustomer.getIsActive());

        // Save the updated customer
        customerRepository.save(existingCustomer);
    }

    private boolean isMobileNumberExists(String mobile) {
        List<Customer> existingCustomers = customerRepository.findByMobile(mobile);
        return !existingCustomers.isEmpty();
    }

    private boolean isEmailExists(String email) {
        List<Customer> existingCustomers = customerRepository.findByEmail(email);
        return !existingCustomers.isEmpty();
    }

}