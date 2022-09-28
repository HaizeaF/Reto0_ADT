package controller;

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
import model.Account;
import model.Customer;
import model.Movement;

/**
 *
 * @author Julen y Haizea
 */
public class ModelFileImplementation implements ModelInterface {

    File customersFile = new File("customersFile.obj");
    File accountsFile = new File("accountsFile.obj");
    File movementsFile = new File("movementsFile.obj");

    FileOutputStream fos = null;
    ObjectOutputStream oos = null;
    MyObjectOutputStream moos = null;
    ObjectInputStream ois = null;
    FileInputStream fis = null;

    @Override
    public void createCustomer(Customer c) {

        if (customersFile.exists()) {
            if (checkCustomer(c.getCustomer_id()) == null) {
                try {
                    fos = new FileOutputStream(customersFile);
                    moos = new MyObjectOutputStream(fos);

                    moos.writeObject(c);
                } catch (FileNotFoundException ex) {
                    ex.getMessage();
                } catch (IOException ex) {
                    ex.getMessage();
                } finally {
                    try {
                        fos.close();
                        moos.close();
                    } catch (IOException ex) {
                        ex.getMessage();
                    }
                }
            } else {
                System.out.println("Customer exist");
            }
        } else {
            try {
                fos = new FileOutputStream(customersFile);
                oos = new ObjectOutputStream(oos);

                oos.writeObject(c);
            } catch (FileNotFoundException ex) {
                ex.getMessage();
            } catch (IOException ex) {
                ex.getMessage();
            } finally {
                try {
                    fos.close();
                    oos.close();
                } catch (IOException ex) {
                    ex.getMessage();
                }
            }
        }
    }

    @Override
    public Customer checkCustomer(Integer idc) {
        List<Object> customersList = new ArrayList<>();
        fileToList(customersFile, customersList);
        Customer customer = null;
        for (int i = 0; i < customersList.size(); i++) {
            if (((Customer) customersList.get(i)).getCustomer_id() == idc) {
                customer = (Customer) customersList.get(i);
            }
        }
        return customer;
    }

    @Override
    public void createAccount(Account a, Integer idc) {
        if (checkCustomer(idc) != null) {
            HashMap<Integer, Customer> customersListAccount;
            HashMap<Integer, Account> accountsListCustomer;
            Customer customer = checkCustomer(idc);
            customersListAccount = a.getCustomers();
            customersListAccount.put(idc, customer);
            a.setCustomers(customersListAccount);

            List<Object> customersList = new ArrayList<>();
            if (accountsFile.exists()) {
                if (checkAccount(a.getAccount_id()) == null) {
                    try {
                        fos = new FileOutputStream(accountsFile);
                        moos = new MyObjectOutputStream(fos);

                        moos.writeObject(a);
                    } catch (FileNotFoundException ex) {
                        ex.getMessage();
                    } catch (IOException ex) {
                        ex.getMessage();
                    } finally {
                        try {
                            fos.close();
                            moos.close();
                        } catch (IOException ex) {
                            ex.getMessage();
                        }
                    }
                } else {
                    System.out.println("Account exist");
                }
            } else {
                try {
                    fos = new FileOutputStream(accountsFile);
                    oos = new ObjectOutputStream(oos);
                    oos.writeObject(a);
                } catch (FileNotFoundException ex) {
                    ex.getMessage();
                } catch (IOException ex) {
                    ex.getMessage();
                } finally {
                    try {
                        fos.close();
                        oos.close();
                    } catch (IOException ex) {
                        ex.getMessage();
                    }
                }
            }

            fileToList(customersFile, customersList);
            for (int i = 0; i < customersList.size(); i++) {
                if (((Customer) customersList.get(i)).getCustomer_id() == idc) {
                    accountsListCustomer = ((Customer) customersList.get(i)).getAccounts();
                    accountsListCustomer.put(a.getAccount_id(), a);
                    ((Customer) customersList.get(i)).setAccounts(accountsListCustomer);
                }
            }
            listToFile(customersList, customersFile);
        } else {
            System.out.println("No customer");
        }
    }

    @Override
    public void addClient(Integer idc, Integer ida) {
        if (customersFile.exists() && accountsFile.exists()) {
            if (checkCustomer(idc) != null && checkAccount(ida) != null) {
                if (checkAccounts(idc).get(ida) == null) {
                    HashMap<Integer, Customer> customersListAccount;
                    HashMap<Integer, Account> accountsListCustomer;
                    List<Object> customersList = new ArrayList<>();
                    List<Object> accountsList = new ArrayList<>();

                    fileToList(customersFile, customersList);
                    for (int i = 0; i < customersList.size(); i++) {
                        if (((Customer) customersList.get(i)).getCustomer_id() == idc) {
                           accountsListCustomer = ((Customer) customersList.get(i)).getAccounts();
                           accountsListCustomer.put(ida, checkAccount(ida));
                           ((Customer) customersList.get(i)).setAccounts(accountsListCustomer);
                        }
                    }
                    listToFile(customersList,customersFile);
                    fileToList(accountsFile, accountsList);
                    for (int i = 0; i < accountsList.size(); i++) {
                        if (((Account) accountsList.get(i)).getAccount_id() == idc) {
                           customersListAccount = ((Account) accountsList.get(i)).getCustomers();
                           customersListAccount.put(idc, checkCustomer(idc));
                           ((Account) accountsList.get(i)).setCustomers(customersListAccount);
                        }
                    }
                    listToFile(accountsList,accountsFile);
                } else {
                    System.out.println("Client exist in Account");
                }
            } else {
                System.out.println("No account/customer");
            }
        } else {
            System.out.println("No file");
        }
    }

    @Override
    public HashMap<Integer, Account> checkAccounts(Integer idc) {
        Customer customer = checkCustomer(idc);
        return customer.getAccounts();
    }

    @Override
    public void accountMovement(Integer ida, Movement m) {
        m.setIdAccount(ida);

        if (movementsFile.exists()) {
            if (checkAccount_Movement(ida).get(m.getMovement_id()) == null) {
                try {

                    fos = new FileOutputStream(movementsFile);
                    moos = new MyObjectOutputStream(fos);

                    moos.writeObject(m);

                } catch (FileNotFoundException ex) {
                    ex.getMessage();
                } catch (IOException ex) {
                    ex.getMessage();
                } finally {
                    try {
                        fos.close();
                        moos.close();
                    } catch (IOException ex) {
                        ex.getMessage();
                    }
                }
            } else {
                System.out.println("Movement exist in account");
            }
        } else {
            try {
                fos = new FileOutputStream(movementsFile);
                oos = new ObjectOutputStream(oos);
                oos.writeObject(m);

            } catch (FileNotFoundException ex) {
                ex.getMessage();
            } catch (IOException ex) {
                ex.getMessage();
            } finally {
                try {
                    fos.close();
                    oos.close();
                } catch (IOException ex) {
                    ex.getMessage();
                }
            }
        }
    }

    @Override
    public HashMap<Integer, Movement> checkAccount_Movement(Integer ida) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static Integer countFile(File customersFile) {

        int cont = 0;
        if (customersFile.exists()) {
            FileInputStream fis = null;
            ObjectInputStream ois = null;
            try {
                fis = new FileInputStream(customersFile);
                ois = new ObjectInputStream(fis);

                Object aux = ois.readObject();

                while (aux != null) {
                    cont++;
                    aux = ois.readObject();
                }

            } catch (EOFException e1) {

            } catch (Exception e2) {
                e2.printStackTrace();
            }

            try {
                ois.close();
                fis.close();
            } catch (IOException e) {

            }
        }
        return cont;
    }

    @Override
    public Account checkAccount(Integer ida) {
        List<Object> accountsList = new ArrayList<>();
        fileToList(accountsFile, accountsList);
        Account account = null;
        for (int i = 0; i < accountsList.size(); i++) {
            if (((Account) accountsList.get(i)).getAccount_id() == ida) {
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
        Integer cont = countFile(file);

        for (int i = 0; i < cont; i++) {
            try {
                obj = ois.readObject();
                list.add(obj);
            } catch (IOException ex) {
                ex.getMessage();
            } catch (ClassNotFoundException ex) {
                ex.getMessage();
            }
        }
    }
}
