package main.EWallet912;

import main.EWallet912.service.ApplicationService;
import main.EWallet912.service.impl.WalletApplicationServiceImpl;


public class main {
    public static void main(String[] args)  {
        new WalletApplicationServiceImpl().start();

    }

}