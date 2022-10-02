/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankadt;

import controller.ModelFileImplementation;
import controller.ModelInterface;
import exception.ExceptionManager;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Objects;
import model.Account;
import model.AccountType;
import model.Customer;
import model.Movement;
import utilidades.Utilidades;

/**
 *
 * @author Julen
 */
public class BankADT {

    public static void main(String[] args) throws ExceptionManager {

        ModelInterface mi = new ModelFileImplementation();

        Integer opcion = 0;
        do {
            System.out.println("\n1.Create customer \n2.Check the data of a customer \n3.Check the accounts of a customer "
                    + "\n4.Create a customer’s account \n5.Add a Customer to an account \n6.Check the data of an account "
                    + "\n7.Make movements in an account \n8.Check movements in an account"
                    + "\n9.Exit");
            System.out.println("Choose an option: ");
            opcion = Utilidades.leerInt();
            switch (opcion) {
                case 1:
                    createCustomer(mi);
                    break;
                case 2:
                    checkCustomerData(mi);
                    break;
                case 3:
                    checkCustomerAccounts(mi);
                    break;
                case 4:
                    createAccount(mi);
                    break;
                case 5:
                    addCustomerToAccount(mi);
                    break;
                case 6:
                    checkAccountData(mi);
                    break;
                case 7:
                    makeMovementsAccount(mi);
                    break;
                case 8:
                    checkAccountMovement(mi);
                    break;
                case 9:
                    System.out.println("BYE :D");
                    break;
                default:
                    System.out.println("Option not valid, im not sorry, BYE D:");
                    break;
            }
        } while (opcion != 9);

    }

    private static void createCustomer(ModelInterface mi) throws ExceptionManager {
        Integer customer_id = null;
        Customer customer;
        Customer checkCustomer = null;
        HashMap<Integer, Account> accountsListCustomer = new HashMap<>();

        System.out.println("Introduce the id of the new Customer: ");
        customer_id = Utilidades.leerInt();
        checkCustomer = mi.checkCustomer(customer_id);
        while (checkCustomer != null) {
            System.out.println("That id is already taken, please choose another one: ");
            customer_id = Utilidades.leerInt();
            checkCustomer = mi.checkCustomer(customer_id);
        }

        System.out.println("Introduce the first name of the Customer: ");
        String firstName = Utilidades.introducirCadena();
        System.out.println("Introduce the last name of the Customer: ");
        String lastName = Utilidades.introducirCadena();
        System.out.println("Introduce the middle initial of the Customer: ");
        String middleInitial = Utilidades.introducirCadena();
        System.out.println("Introduce the street of the Customer: ");
        String street = Utilidades.introducirCadena();
        System.out.println("Introduce the city of the Customer: ");
        String city = Utilidades.introducirCadena();
        System.out.println("Introduce the state of the Customer: ");
        String state = Utilidades.introducirCadena();
        System.out.println("Introduce the zip of the Customer: ");
        Integer zip = Utilidades.leerInt();
        System.out.println("Introduce the phone of the Customer: ");
        Integer phone = Utilidades.leerInt();
        System.out.println("Introduce the email of the Customer: ");
        String email = Utilidades.introducirCadena();

        customer = new Customer(customer_id, firstName, lastName, middleInitial, street, city, state, zip, phone, email, accountsListCustomer);
        mi.createCustomer(customer);

    }

    private static void checkCustomerData(ModelInterface mi) throws ExceptionManager {

        Integer customer_id = null;
        Customer checkCustomer = null;

        System.out.println("Introduce the id of the Customer you want to the data: ");
        customer_id = Utilidades.leerInt();
        checkCustomer = mi.checkCustomer(customer_id);
        while (checkCustomer == null) {
            System.out.println("Id not valid, please try again: ");
            customer_id = Utilidades.leerInt();
            checkCustomer = mi.checkCustomer(customer_id);
        }

        System.out.println("Name: " + checkCustomer.getFirstName()
                + " last Name: " + checkCustomer.getLastName()
                + " middle Initial: " + checkCustomer.getMiddleInitial()
                + " street: " + checkCustomer.getStreet()
                + " city: " + checkCustomer.getCity()
                + " state: " + checkCustomer.getState()
                + " Zip: " + checkCustomer.getZip()
                + " phone: " + checkCustomer.getPhone()
                + " email: " + checkCustomer.getEmail());

    }

    private static void checkCustomerAccounts(ModelInterface mi) throws ExceptionManager {
        Integer customer_id = null;
        Customer checkCustomer = null;
        int cont = 1;
        HashMap<Integer, Account> accountsList = new HashMap<>();

        System.out.println("Introduce the id of the Customer you want to the data: ");
        customer_id = Utilidades.leerInt();
        checkCustomer = mi.checkCustomer(customer_id);
        while (checkCustomer == null) {
            System.out.println("Id not valid, please try again: ");
            customer_id = Utilidades.leerInt();
            checkCustomer = mi.checkCustomer(customer_id);
        }

        accountsList = checkCustomer.getAccounts();

        if (checkCustomer.getAccounts().size() > 0) {
            for (Account a : accountsList.values()) {
                System.out.println("Account id " + cont + ": " + a.getAccount_id());
                cont++;
            }

        } else {
            System.out.println("SORRY, This Customer doesn´t have any Accounts.");
        }

    }

    private static void createAccount(ModelInterface mi) throws ExceptionManager {
        Integer customer_id = null;
        Account account;
        Customer checkCustomer;
        Character standarOrCredit;
        AccountType type = null;
        HashMap<Integer, Customer> customersListAccount = new HashMap<>();

        System.out.println("Introduce the id of the Customers Account: ");
        customer_id = Utilidades.leerInt();
        checkCustomer = mi.checkCustomer(customer_id);
        while (checkCustomer == null) {
            System.out.println("Id not valid, please try again: ");
            customer_id = Utilidades.leerInt();
            checkCustomer = mi.checkCustomer(customer_id);
        }
        System.out.println("Introduce the id of the new Account: ");
        Integer account_id = Utilidades.leerInt();
        account = mi.checkAccount(account_id);
        while (account != null) {
            System.out.println("That id is already taken, please choose another one: ");
            account_id = Utilidades.leerInt();
            account = mi.checkAccount(account_id);
        }
        System.out.println("Introduce a sort description of the Account: ");
        String description = Utilidades.introducirCadena();
        System.out.println("Introduce the current balance of the Account: ");
        Float balance = Utilidades.leerFloat();
        System.out.println("Introduce the current amount of creditLine of the Account: ");
        Float creditLine = Utilidades.leerFloat();
        System.out.println("Introduce the beginning balance of the Account: ");
        Float beginBalance = Utilidades.leerFloat();
        System.out.println("Introduce the date of the beginning balance of the Account: ");
        LocalDateTime beginBalanceTimestamp = Utilidades.leerFechaYHoraDMA();
        do {
            System.out.println("Introduce the type of the Account: (S STANDARD / C CREDIT)");
            standarOrCredit = Utilidades.leerChar();
            if (standarOrCredit == 's' || standarOrCredit == 'S') {
                type = AccountType.STANDARD;
            } else if (standarOrCredit == 'c' || standarOrCredit == 'C') {
                type = AccountType.CREDIT;
            } else {
                System.out.println("That option is not valid");
            }
        } while (!(standarOrCredit == 's' || standarOrCredit == 'S' || standarOrCredit == 'c' || standarOrCredit == 'C'));

        account = new Account(account_id, description, balance, creditLine, beginBalance, beginBalanceTimestamp, type, customersListAccount);

        mi.createAccount(account, checkCustomer.getCustomer_id());
    }

    private static void addCustomerToAccount(ModelInterface mi) throws ExceptionManager {

        Integer account_id = null;
        Integer customer_id = null;
        Customer checkCustomer;
        Account checkAccount;

        System.out.println("Introduce the id of the Account you want to add a Customer: ");
        account_id = Utilidades.leerInt();
        checkAccount = mi.checkAccount(account_id);
        while (checkAccount == null) {
            System.out.println("Id not valid, please try again: ");
            account_id = Utilidades.leerInt();
            checkAccount = mi.checkAccount(customer_id);
        }

        System.out.println("Introduce the id of the Customer: ");
        customer_id = Utilidades.leerInt();
        checkCustomer = mi.checkCustomer(customer_id);
        while (checkCustomer == null) {
            System.out.println("Id not valid, please try again: ");
            customer_id = Utilidades.leerInt();
            checkCustomer = mi.checkCustomer(customer_id);
        }

        mi.addCustomer(checkCustomer.getCustomer_id(), checkAccount.getAccount_id());

    }

    private static void checkAccountData(ModelInterface mi) throws ExceptionManager {
        Integer account_id = null;
        Account checkAccount = null;

        System.out.println("Introduce the id of the Account you want to the data: ");
        account_id = Utilidades.leerInt();
        checkAccount = mi.checkAccount(account_id);
        while (checkAccount == null) {
            System.out.println("Id not valid, please try again: ");
            account_id = Utilidades.leerInt();
            checkAccount = mi.checkAccount(account_id);
        }

        System.out.println("Description: " + checkAccount.getDescription()
                + " balance: " + checkAccount.getBalance()
                + " creditLine: " + checkAccount.getCreditLine()
                + " begin Balance: " + checkAccount.getBeginBalance()
                + " begin Balance Times tamp: " + checkAccount.getBeginBalanceTimestamp()
                + " type: " + checkAccount.getType());
    }

    private static void makeMovementsAccount(ModelInterface mi) throws ExceptionManager {
        Integer account_id = null;
        Account checkAccount = null;
        Movement movement = null;

        System.out.println("Introduce the id of the Account you want to add a movement: ");
        account_id = Utilidades.leerInt();
        checkAccount = mi.checkAccount(account_id);
        while (checkAccount == null) {
            System.out.println("Id not valid, please try again: ");
            account_id = Utilidades.leerInt();
            checkAccount = mi.checkAccount(account_id);
        }

        System.out.println("Introduce the id of the movement: ");
        Integer movement_id = Utilidades.leerInt();
        while(movementExist(movement_id, account_id, mi)) {
            System.out.println("That id is already taken, please choose another one: ");
            movement_id = Utilidades.leerInt();
        }
        System.out.println("Introduce the timeStamp of the movement: ");
        LocalDateTime timeStamp = Utilidades.leerFechaYHoraDMA();
        System.out.println("Introduce the amount of CASH the movement has: ");
        Float amount = Utilidades.leerFloat();
        System.out.println("The actual balance of the Account is: ");
        //DAR UNA VUELTA
        Float balance = checkAccount.getBalance();
        Float finalBalance = balance + amount;
        System.out.println(finalBalance);
        System.out.println("Introduce the description of the Movement: ");
        String description = Utilidades.introducirCadena();

        movement = new Movement(movement_id, timeStamp, amount, finalBalance, description, account_id);
        mi.accountMovement(checkAccount.getAccount_id(), movement);

    }

    private static void checkAccountMovement(ModelInterface mi) throws ExceptionManager {
        Integer account_id = null;
        Account checkAccount = null;
        Integer cont = 1;
        HashMap<Integer, Movement> movementsAccountList = new HashMap<>();

        System.out.println("Introduce the id of the Account you want to check the Movements: ");
        account_id = Utilidades.leerInt();
        checkAccount = mi.checkAccount(account_id);
        while (checkAccount == null) {
            System.out.println("Id not valid, please try again: ");
            account_id = Utilidades.leerInt();
            checkAccount = mi.checkAccount(account_id);
        }

        movementsAccountList = mi.checkAccount_Movement(checkAccount.getAccount_id());

        System.out.println("Account id: " + checkAccount.getAccount_id());
        for (Movement a : movementsAccountList.values()) {
            System.out.println("Movement id " + cont + " :" + a.getMovement_id()
                    + " Movement amount: " + a.getAmount());
            cont++;
        }
        if (movementsAccountList.size() > 0) {
            System.out.println("\n");
        } else {
            System.out.println("SORRY, This Account doesn´t have any Movements.");
        }
    }

    private static boolean movementExist(Integer movement_id, Integer ida, ModelInterface mi) throws ExceptionManager {
        HashMap<Integer, Movement> movementsList = mi.checkAccount_Movement(ida);
        for (Movement m: movementsList.values()) {
            if (Objects.equals(m.getMovement_id(), movement_id)) {
                return true;
            }
        }
        return false;
    }
}
