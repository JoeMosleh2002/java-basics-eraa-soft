package main.EWallet912.service.impl;

import main.EWallet912.model.Account;
import main.EWallet912.model.WalletSystem;
import main.EWallet912.service.AccountService;
import main.EWallet912.service.ApplicationService;

import java.util.Objects;
import java.util.Scanner;


/**
 * Application service responsible for handling the main wallet application flow.
 *
 * <p>This service provides the console-based user interface for:</p>
 * <ul>
 *     <li>User login</li>
 *     <li>User account registration</li>
 *     <li>Accessing the user's wallet profile</li>
 *     <li>Logging out of the application</li>
 * </ul>
 *
 * <p>The service communicates with {@link AccountService} to perform
 * account-related operations.</p>
 */
public class WalletApplicationServiceImpl implements ApplicationService {

    private Scanner scanner = new Scanner(System.in);

    private AccountService accountService = new AccountServiceImpl();

    /**
     * Starts the wallet application.
     *
     * <p>Displays the main menu and allows the user to choose between
     * login, signup, and exit operations.</p>
     *
     * <p>The application allows up to four invalid menu selections.
     * After reaching the limit, the application terminates and asks
     * the user to contact the administrator.</p>
     */
    @Override
    public void start() {
        System.out.println("-> Welcome to " + WalletSystem.name);

        Integer count = 0;

        while (true) {

            System.out.println("please choose......");
            System.out.println("1.login    2.signup     3.Exit");

            int choose = scanner.nextInt();

            Boolean isExit = false;

            switch (choose) {

                case 1:
                    login();
                    break;

                case 2:
                    signup();
                    break;

                case 3:
                    System.out.println("have a nice day :)");
                    isExit = true;
                    break;

                default:
                    System.out.println("invalid choose :(");
                    count++;
            }

            if (isExit) {
                break;
            }

            if (count == 4) {
                System.out.println("pls contact with Admin :(");
                break;
            }
        }
    }

    /**
     * Handles the user login process.
     *
     * <p>The user provides a username and password. The credentials are
     * passed to {@link AccountService} to find an existing account.</p>
     *
     * <p>If the account exists, the user is redirected to the main
     * wallet profile. Otherwise, an error message is displayed.</p>
     */
    private void login() {

        System.out.print("Please enter your username: ");
        String userName = scanner.next();

        System.out.print("Please enter your password: ");
        String password = scanner.next();

        Account account = new Account(userName, password);

        Account existedAccount =
                accountService.getAccountByUsernameAndPassword(account);

        if (Objects.isNull(existedAccount)) {

            System.out.println(
                    "account not exist pls check username and password :("
            );

        } else {

            System.out.println("success login :)");

            mainProfile(existedAccount);
        }
    }

    /**
     * Handles the new account registration process.
     *
     * <p>The user provides the required account information including
     * username, password, phone number, and age.</p>
     *
     * <p>The account is created through {@link AccountService}. If the
     * username already exists, account creation fails.</p>
     */
    private void signup() {

        System.out.print("Please enter your username: ");
        String userName = scanner.next();

        System.out.print("Please enter your password: ");
        String password = scanner.next();

        System.out.print("Please enter your phone number: ");
        String phoneNumber = scanner.next();

        System.out.print("Please enter your age: ");
        Float age = scanner.nextFloat();

        Account accountCreate =
                new Account(userName, password, phoneNumber, age);

        // Create account
        accountCreate = accountService.createAccount(accountCreate);

        if (Objects.isNull(accountCreate)) {

            System.out.println(
                    "account failed to create because username already exist...."
            );

        } else {

            System.out.println("account created success...");

            mainProfile(accountCreate);
        }
    }

    /**
     * Displays the main wallet profile menu for the authenticated user.
     *
     * <p>After successful login or signup, the user can perform wallet
     * operations such as depositing money, withdrawing money, transferring
     * money, viewing the balance, viewing account details, changing the
     * password, or logging out.</p>
     *
     * @param account the currently authenticated user's account
     */
    private void mainProfile(Account account) {

        System.out.println(
                "1.deposit   " +
                "2.withdraw  " +
                "3.Transfer   " +
                "4.show balance    " +
                "5.show details   " +
                "6.Change Password   " +
                "7.logout"
        );

        // TODO: Continue implementing wallet operations
    }
}
