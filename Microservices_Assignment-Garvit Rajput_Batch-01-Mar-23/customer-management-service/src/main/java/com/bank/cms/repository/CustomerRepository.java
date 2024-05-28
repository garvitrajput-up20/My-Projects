package com.bank.cms.repository;

import com.bank.cms.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {
    Customer findByCustomerId(String customerId);
    Customer deleteByCustomerId(String customerId);

    List<Customer> findByMobile(String mobile);

    List<Customer> findByEmail(String email);
}