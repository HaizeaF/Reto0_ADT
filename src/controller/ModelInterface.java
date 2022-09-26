/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.HashMap;
import model.Account;
import model.Customer;
import model.Movement;

/**
 *
 * @author 2dam
 */
public interface ModelInterface {
    public void createCustomer(Customer c);
    public Customer checkCustomer(Integer idc);
    public void createAccount(Account a, Integer idc);
    public void addClient(Integer idc, Integer ida);
    public HashMap<Integer, Account> checkAccounts(Integer idc);
    public void accountMovement(Integer ida, Movement m);
    public Account checkAccount(Integer ida);
    public HashMap<Integer, Movement> checkAccount_Movement(Integer ida);
}
