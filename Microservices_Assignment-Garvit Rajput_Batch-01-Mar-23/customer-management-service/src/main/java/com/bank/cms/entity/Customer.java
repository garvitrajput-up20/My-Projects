package com.bank.cms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String customerId;

    @NonNull
    private String name;

    @Column(unique = true)
    @Email
    private String email;

    @Column(unique = true)
    @Size(min = 10, max = 10, message = "Please Enter Valid Mobile Number")
    @NonNull
    private String mobile;

    @NonNull
    private Boolean isActive;
}