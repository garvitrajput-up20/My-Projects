package com.bank.ams.service;

import com.bank.ams.entity.Account;
import com.bank.ams.enums.TransactionTypes;
import com.bank.ams.dto.Customer;
import com.bank.ams.repository.AccountRepository;
import com.bank.ams.repository.CustomerFeignClient;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerFeignClient customerFeignClient;

    public boolean isAccountExistsForCustomer(String customerId) {
        return accountRepository.existsByCustomerId(customerId);
    }

    public void createAccount(Account account){
        accountRepository.save(account);
    }

    public Account getAccountById(String accountId){
        return (Account) accountRepository.findByAccountId(accountId);
    }

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    public void processTransaction(String accountId, double amount, TransactionTypes transactionType) {
        Account account = accountRepository.findByAccountId(accountId);
        Customer customer = customerFeignClient.findByCustomerId(account.getCustomerId());

        if (customer != null && customer.getIsActive()) {
            switch (transactionType) {
                case DEPOSIT:
                    deposit(account, amount);
                    break;
                case WITHDRAW:
                    withdrawal(account, amount);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported transaction type");
            }
        } else {
            throw new IllegalArgumentException("Invalid account or customer is not active");
        }
    }

    private void deposit(@NotNull Account account, double amount) {
        double currentBalance = account.getAccountBalance();
        account.setAccountBalance(currentBalance + amount);
        accountRepository.save(account);
    }

    private void withdrawal(Account account, double amount) {
        double currentBalance = account.getAccountBalance();
        if (currentBalance >= amount) {
            account.setAccountBalance(currentBalance - amount);
            accountRepository.save(account);
        } else {
            throw new IllegalArgumentException("Insufficient balance for withdrawal");
        }
    }
}