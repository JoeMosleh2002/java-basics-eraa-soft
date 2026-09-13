package main.EWallet912.service;

import main.EWallet912.model.Account;

public interface AccountService {
    Account createAccount(Account account);

    Account getAccountByUsernameAndPassword(Account account);
}
