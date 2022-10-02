package controller;

import exception.ExceptionManager;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import model.Account;
import model.Customer;
import model.Movement;
import utilidades.Utilidades;

/**
 *
 * @author Julen y Haizea
 */
public class ModelFileImplementation implements ModelInterface {

    File customersFile = new File("customersFile.obj");
    File accountsFile = new File("accountsFile.obj");
    File movementsFile = new File("movementsFile.obj");

    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;

    @Override
    public void createCustomer(Customer c) throws ExceptionManager {

        if (customersFile.exists()) {
            try {
                oos = new MyObjectOutputStream(new FileOutputStream(customersFile, true));

                oos.writeObject(c);
            } catch (FileNotFoundException ex) {
                String error = "File not found.";
                ExceptionManager exception = new ExceptionManager(error);
                throw exception;
            } catch (IOException ex) {
                ex.getMessage();
            }
        } else {
            try {
                oos = new ObjectOutputStream(new FileOutputStream(customersFile));

                oos.writeObject(c);
            } catch (FileNotFoundException ex) {
                String error = "File not found.";
                ExceptionManager exception = new ExceptionManager(error);
                throw exception;
            } catch (IOException ex) {
                ex.getMessage();
            }
        }
        try {
            if (oos != null) {
                oos.close();
            }
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    @Override
    public Customer checkCustomer(Integer idc) throws ExceptionManager {
        List<Object> customersList = new ArrayList<>();
        fileToList(customersFile, customersList);
        Customer customer = null;
        for (int i = 0; i < customersList.size(); i++) {
            if (Objects.equals(((Customer) customersList.get(i)).getCustomer_id(), idc)) {
                customer = (Customer) customersList.get(i);
            }
        }
        return customer;
    }

    @Override
    public void createAccount(Account a, Integer idc) throws ExceptionManager {
        HashMap<Integer, Customer> customersListAccount;
        HashMap<Integer, Account> accountsListCustomer;
        Customer customer = checkCustomer(idc);
        customersListAccount = a.getCustomers();
        customersListAccount.put(idc, customer);
        a.setCustomers(customersListAccount);

        List<Object> customersList = new ArrayList<>();
        if (accountsFile.exists()) {
            try {
                oos = new MyObjectOutputStream(new FileOutputStream(accountsFile, true));

                oos.writeObject(a);
            } catch (FileNotFoundException ex) {
                String error = "File not found.";
                ExceptionManager exception = new ExceptionManager(error);
                throw exception;
            } catch (IOException ex) {
                ex.getMessage();
            }
        } else {
            try {
                oos = new ObjectOutputStream(new FileOutputStream(accountsFile));
                oos.writeObject(a);
            } catch (FileNotFoundException ex) {
                String error = "File not found.";
                ExceptionManager exception = new ExceptionManager(error);
                throw exception;
            } catch (IOException ex) {
                ex.getMessage();
            }
        }
        try {
            if (oos != null) {
                oos.close();
            }
        } catch (IOException ex) {
            ex.getMessage();
        }
        fileToList(customersFile, customersList);
        for (int i = 0; i < customersList.size(); i++) {
            if (Objects.equals(((Customer) customersList.get(i)).getCustomer_id(), idc)) {
                accountsListCustomer = ((Customer) customersList.get(i)).getAccounts();
                accountsListCustomer.put(a.getAccount_id(), a);
                ((Customer) customersList.get(i)).setAccounts(accountsListCustomer);
            }
        }
        listToFile(customersList, customersFile);
    }

    @Override
    public void addCustomer(Integer idc, Integer ida) throws ExceptionManager {
        if (customersFile.exists() && accountsFile.exists()) {
            if (checkAccounts(idc).get(ida) == null) {
                HashMap<Integer, Customer> customersListAccount;
                HashMap<Integer, Account> accountsListCustomer;
                List<Object> customersList = new ArrayList<>();
                List<Object> accountsList = new ArrayList<>();

                fileToList(customersFile, customersList);
                for (int i = 0; i < customersList.size(); i++) {
                    if (Objects.equals(((Customer) customersList.get(i)).getCustomer_id(), idc)) {
                        accountsListCustomer = ((Customer) customersList.get(i)).getAccounts();
                        accountsListCustomer.put(ida, checkAccount(ida));
                        ((Customer) customersList.get(i)).setAccounts(accountsListCustomer);
                    }
                }
                listToFile(customersList, customersFile);
                fileToList(accountsFile, accountsList);
                for (int i = 0; i < accountsList.size(); i++) {
                    if (Objects.equals(((Account) accountsList.get(i)).getAccount_id(), ida)) {
                        customersListAccount = ((Account) accountsList.get(i)).getCustomers();
                        customersListAccount.put(idc, checkCustomer(idc));
                        ((Account) accountsList.get(i)).setCustomers(customersListAccount);
                    }
                }
                listToFile(accountsList, accountsFile);
            } else {
                System.out.println("Client exist in Account");
            }
        } else {
            System.out.println("No file");
        }
    }

    @Override
    public HashMap<Integer, Account> checkAccounts(Integer idc) throws ExceptionManager {
        Customer customer = checkCustomer(idc);
        return customer.getAccounts();
    }

    @Override
    public void accountMovement(Integer ida, Movement m) throws ExceptionManager {
        m.setIdAccount(ida);
        List<Object> accountsList = new ArrayList<>();

        if (movementsFile.exists()) {
            try {
                oos = new MyObjectOutputStream(new FileOutputStream(movementsFile, true));

                oos.writeObject(m);

            } catch (FileNotFoundException ex) {
                String error = "File not found.";
                ExceptionManager exception = new ExceptionManager(error);
                throw exception;
            } catch (IOException ex) {
                ex.getMessage();
            }
        } else {
            try {
                oos = new ObjectOutputStream(new FileOutputStream(movementsFile));
                oos.writeObject(m);

            } catch (FileNotFoundException ex) {
                String error = "File not found.";
                ExceptionManager exception = new ExceptionManager(error);
                throw exception;
            } catch (IOException ex) {
                ex.getMessage();
            }
        }
        try {
            if (oos != null) {
                oos.close();
            }
        } catch (IOException ex) {
            ex.getMessage();
        }

        fileToList(accountsFile, accountsList);
        for (int i = 0; i < accountsList.size(); i++) {
            if (Objects.equals(((Account) accountsList.get(i)).getAccount_id(), ida)) {
                Float finalBalance = ((Account) accountsList.get(i)).getBalance() + m.getAmount();
                ((Account) accountsList.get(i)).setBalance(finalBalance);
            }
        }
        listToFile(accountsList, accountsFile);
    }

    @Override
    public HashMap<Integer, Movement> checkAccount_Movement(Integer ida) throws ExceptionManager {
        HashMap<Integer, Movement> movementsListAccount = new HashMap<>();
        List<Object> movementsList = new ArrayList<>();
        fileToList(movementsFile, movementsList);
        for (int i = 0; i < movementsList.size(); i++) {
            if (Objects.equals(((Movement) movementsList.get(i)).getIdAccount(), ida)) {
                movementsListAccount.put(((Movement) movementsList.get(i)).getMovement_id(), (Movement) movementsList.get(i));
            }
        }
        return movementsListAccount;
    }

    @Override
    public Account checkAccount(Integer ida) throws ExceptionManager {
        List<Object> accountsList = new ArrayList<>();
        fileToList(accountsFile, accountsList);
        Account account = null;
        for (int i = 0; i < accountsList.size(); i++) {
            if (Objects.equals(((Account) accountsList.get(i)).getAccount_id(), ida)) {
                account = (Account) accountsList.get(i);
            }
        }
        return account;
    }

    private static void listToFile(List<Object> list, File file) {
        ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            for (int i = 0; i < list.size(); i++) {
                oos.writeObject(list.get(i));
            }
        } catch (IOException ex) {
            ex.getMessage();
        } finally {
            try {
                oos.close();
            } catch (IOException ex) {
                ex.getMessage();
            }
        }
    }

    private static void fileToList(File file, List<Object> list) {
        ObjectInputStream ois = null;
        Object obj;
        Integer cont = Utilidades.calculoFichero(file);
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            for (int i = 0; i < cont; i++) {
                obj = ois.readObject();
                list.add(obj);
            }

        } catch (IOException | ClassNotFoundException ex) {
            ex.getMessage();
        }

    }
}
