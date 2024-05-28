package com.bank.ams.entity;

import com.bank.ams.dto.Customer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String accountId;

    private double accountBalance;

    @NotBlank(message = "Customer ID must not be blank")
    private String customerId;

    @Transient
    private Customer customer;
}