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
    public void CreateCustomer(Customer c) {

        if (customersFile.exists()) {
            if (CheckCustomer(c.getCustomer_id()) == null) {

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
    public Customer CheckCustomer(Integer idc) {

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
    public void CreateAccount(Account a, Integer idc) {

        if (accountFile.exists()) {
            if (CheckCustomer(a.getAccount_id()) == null) {

                try {

                    fos = new FileOutputStream(accountFile);
                    moos = new MyObjectOutputStream(fos);

                    moos.writeObject(a);

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

    }

    @Override
    public void AddClient(Integer idc, Integer ida) {
        
    }

    @Override
    public Account CheckAccount(Integer idc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void AccountMovement(Integer ida, Movement m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap<Integer, Movement> CheckAccount_Movement(Integer ida) {
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

}
