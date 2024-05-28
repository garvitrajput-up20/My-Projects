package com.bank.ams.repository;

import com.bank.ams.dto.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "customer-management-service")
public interface CustomerFeignClient {

    @GetMapping("/customer/fetch-by-id")
    Customer findByCustomerId(@RequestParam("customerId") String customerId);

}