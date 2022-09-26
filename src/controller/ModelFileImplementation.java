package controller;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Customer;
import model.Movement;

/**
 *
 * @author Julen y Haizea
 */
public class ModelFileImplementation implements ModelInterface {

    File customersFile = new File("customersFile.obj");
    File accountFile = new File("accountFile.obj");

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

                } catch (IOException ex) {

                }

                try {
                    fos.close();
                    moos.close();
                } catch (IOException ex) {

                }

            } else {
                System.out.println("VACIO");
            }

        } else {
            try {

                fos = new FileOutputStream(customersFile);
                oos = new ObjectOutputStream(oos);

                oos.writeObject(c);

            } catch (FileNotFoundException ex) {

            } catch (IOException ex) {

            }

            try {
                fos.close();
                oos.close();
            } catch (IOException ex) {

            }
        }

    }

    @Override
    public Customer checkCustomer(Integer idc) {

        Customer customer = null;

        try {
            fis = new FileInputStream(customersFile);
            ois = new ObjectInputStream(fis);

        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        }

        Integer cont = countFile(customersFile);

        for (int i = 0; i < cont; i++) {
            try {
                customer = (Customer) ois.readObject();
                if (idc == customer.getCustomer_id()) {
                    i = cont;
                    return customer;
                }
            } catch (IOException ex) {

            } catch (ClassNotFoundException ex) {

            }
        }

        try {
            fos.close();
            oos.close();
        } catch (IOException ex) {

        }

        return customer;

    }

    @Override
    public void createAccount(Account a, Integer idc) {
        if (checkCustomer(idc) != null) {
            HashMap<Integer,Customer> customersList;
            Customer customer = checkCustomer(idc);
            customersList = a.getCustomers();
            customersList.put(idc, customer);
            a.setCustomers(customersList);
            if (accountFile.exists()) {
                if (checkAccounts(idc).get(a.getAccount_id()) == null) {

                    try {

                        fos = new FileOutputStream(accountFile);
                        moos = new MyObjectOutputStream(fos);

                        moos.writeObject(a);

                    } catch (FileNotFoundException ex) {

                    } catch (IOException ex) {

                    } finally {
                        try {
                        fos.close();
                        moos.close();
                        } catch (IOException ex) {

                        }
                    }
                } else {
                    System.out.println("Account exist");
                }

            } else {
                try {

                    fos = new FileOutputStream(accountFile);
                    oos = new ObjectOutputStream(oos);
                    oos.writeObject(a);

                } catch (FileNotFoundException ex) {

                } catch (IOException ex) {

                }

                try {
                    fos.close();
                    oos.close();
                } catch (IOException ex) {

                }
            }
            
            try {
                fos = new FileOutputStream(customersFile);
                moos = new MyObjectOutputStream(fos);
                
                HashMap<Integer,Account> accountsList = customer.getAccounts();
                accountsList.put(a.getAccount_id(), a);
                customer.setAccounts(accountsList);
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
            System.out.println("No customer");
        }

    }

    @Override
    public void addClient(Integer idc, Integer ida) {
        if (customersFile.exists() && accountFile.exists()) {
            if (checkCustomer(idc) != null && checkAccount(ida) != null) {
                if (checkAccounts(idc).get(ida) == null) {
                    
                } else {
                    System.out.println("Client exist in Account");
                }
            }
        } else {
            System.out.println("No file");
        }
    }

    @Override
    public HashMap<Integer,Account> checkAccounts(Integer ida) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void accountMovement(Integer ida, Movement m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap<Integer, Movement> checkAccount_Movement(Integer ida) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Integer countFile(File customersFile) {

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
