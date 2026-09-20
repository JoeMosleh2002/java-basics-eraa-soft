package main.EWallet912.service.impl;

import main.EWallet912.enums.DepositStatus;
import main.EWallet912.model.Account;
import main.EWallet912.model.WalletSystem;
import main.EWallet912.service.AccountService;

import java.util.Optional;

public class AccountServiceImpl implements AccountService {

    private WalletSystem walletSystem = new WalletSystem();

    @Override
    public Account createAccount(Account account) {
        boolean isAccountExistWithSameUserName = walletSystem.getAccounts().stream()
                .anyMatch(acc -> acc.getUserName().equals(account.getUserName()));

        if (isAccountExistWithSameUserName) {
            return null;
        }

        walletSystem.getAccounts().add(account);
        return account;
    }

    @Override
    public Account getAccountByUsernameAndPassword(Account account) {
        Optional<Account> existedAccount = walletSystem.getAccounts().stream()
                .filter(acc -> acc.getUserName().equals(account.getUserName()) &&
                               acc.getPassword().equals(account.getPassword())).findFirst();

        if (existedAccount.isPresent()) {
            return existedAccount.get();
        }

        return null;
    }

    @Override
    public DepositStatus deposit(Account account, double amount) {
        // 1. make sure account wallet
        Optional<Account> accountOptional = walletSystem.getAccounts().stream().filter(acc -> acc.getUserName().equals(account.getUserName())).findFirst();
        if (accountOptional.isEmpty()) {
            return DepositStatus.ACCOUNT_NOT_EXIST;
        }
        // 2. make sue amount deposit >= 100
        if (amount < 100) {
            return DepositStatus.AMOUNT_LESS_THAN_MINIMUM;
        }
        // 3. make sue amount deposit <= 12000
        if (amount > 12000) {
            return DepositStatus.AMOUNT_GREATER_THAN_MAXIMUM;
        }

        // 3. deposit feature
        double currentBalance = accountOptional.get().getBalance();
        accountOptional.get().setBalance(currentBalance + amount);
        return DepositStatus.SUCCESS;
    }

    @Override
    public Double withdraw(Account account, double amount) {
        // 1. make sure account wallet
        Optional<Account> accountOptional = walletSystem.getAccounts().stream().filter(acc -> acc.getUserName().equals(account.getUserName())).findFirst();
        if (accountOptional.isEmpty()) {
            return -1.0;
        }
        // 2. make sue amount deposit >= 100
        if (amount < 100) {
            return -2.0;
        }
        // 3. make sue amount deposit <= 12000
        if (amount > 12000) {
            return -3.0;
        }

        if (accountOptional.get().getBalance() < amount) {
            return -4.0;
        }

        double currentBalance = accountOptional.get().getBalance();
        accountOptional.get().setBalance(currentBalance - amount);
        return accountOptional.get().getBalance();
    }

    @Override
    public Double getAccountBalance(Account account) {
        Optional<Account> accountOptional = walletSystem.getAccounts().stream()
                .filter(acc -> acc.getUserName().equals(account.getUserName())).findFirst();
        if (accountOptional.isEmpty()) {
            throw new RuntimeException("account not exist");
        }

        return accountOptional.get().getBalance();
    }

}
