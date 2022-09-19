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
    public void CreateCustomer(Customer c);
    public Customer CheckCustomer(Integer idc);
    public void CreateAccount(Account a, Integer idc);
    public void AddClient(Integer idc, Integer ida);
    public Account CheckAccount(Integer idc);
    public void AccountMovement(Integer ida, Movement m);
    public HashMap<Integer, Movement> CheckAccount_Movement(Integer ida);
    
    
}
