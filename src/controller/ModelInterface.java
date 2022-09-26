/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import exception.ExceptionManager;
import java.util.HashMap;
import model.Account;
import model.Customer;
import model.Movement;

/**
 *
 * @author 2dam
 */
public interface ModelInterface {
    public void createCustomer(Customer c)throws ExceptionManager;
    public Customer checkCustomer(Integer idc)throws ExceptionManager;
    public void createAccount(Account a, Integer idc)throws ExceptionManager;
    public void addClient(Integer idc, Integer ida)throws ExceptionManager;
    public HashMap<Integer, Account> checkAccounts(Integer idc)throws ExceptionManager;
    public void accountMovement(Integer ida, Movement m)throws ExceptionManager;
    public Account checkAccount(Integer ida) throws ExceptionManager;
    public HashMap<Integer, Movement> checkAccount_Movement(Integer ida)throws ExceptionManager;
    
    
}
