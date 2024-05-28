package com.bank.ams.controller;

import com.bank.ams.entity.Account;
import com.bank.ams.enums.TransactionTypes;
import com.bank.ams.exceptions.CustomException;
import com.bank.ams.dto.Customer;
import com.bank.ams.repository.CustomerFeignClient;
import com.bank.ams.service.AccountService;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final CustomerFeignClient customerFeignClient;

    @PostMapping("/add-account")
    public ResponseEntity<?> createAccount(@RequestBody Account account) {
        try {
            if (accountService.isAccountExistsForCustomer(account.getCustomerId())) {
                throw new CustomException(HttpStatus.CONFLICT.value(), "An account already exists for customer with ID: " + account.getCustomerId());
            }

            Customer customer = customerFeignClient.findByCustomerId(account.getCustomerId());
            if (customer == null) {
                throw new CustomException(HttpStatus.NOT_FOUND.value(), "Customer not found with ID: " + account.getCustomerId());
            }

            accountService.createAccount(account);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (FeignException.NotFound e) {
            throw new CustomException(HttpStatus.NOT_FOUND.value(), "Customer not found with ID: " + account.getCustomerId());
        } catch (CustomException e) {
            throw e; // Re-throw custom exception
        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
    }


    @GetMapping("/list-accounts")
    public ResponseEntity<List<Account>> listAccounts() {
        try {
            List<Account> accounts = accountService.getAllAccounts();
            if (accounts.isEmpty()) {
                throw new CustomException(HttpStatus.NOT_FOUND.value(), "No Accounts Found");
            }
            accounts.forEach(account -> account.setCustomer(customerFeignClient.findByCustomerId(account.getCustomerId())));
            return ResponseEntity.status(HttpStatus.OK).body(accounts);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
    }

    @GetMapping("/fetch-by-id")
    public Account getAccountWithCustomer(@RequestParam String accountId) {
        try {
            Account account = accountService.getAccountById(accountId);
            account.setCustomer(customerFeignClient.findByCustomerId(account.getCustomerId()));
            return account;
        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Account Not Found");
        }
    }

    @PostMapping("/add-money")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> addMoney(@RequestParam String accountId, @RequestParam double amount) {
        try {
            accountService.processTransaction(accountId, amount, TransactionTypes.DEPOSIT);
            Account account = accountService.getAccountById(accountId);
            double updatedBalance = account.getAccountBalance();
            return ResponseEntity.ok("Money added successfully. Available balance: " + updatedBalance);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Account not found or unable to process transaction");
        }
    }

    @PostMapping("/withdraw-money")
    @ResponseStatus(HttpStatus.OK)
    public void withdrawMoney(@RequestParam String accountId, @RequestParam double amount) {
        try {
            accountService.processTransaction(accountId, amount, TransactionTypes.WITHDRAW);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
    }
}