package main.EWallet912.service.impl;

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

}
