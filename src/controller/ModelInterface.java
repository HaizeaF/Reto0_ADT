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
    public void CreateCustomer(Customer c)throws ExceptionManager;
    public Customer CheckCustomer(Integer idc)throws ExceptionManager;
    public void CreateAccount(Account a)throws ExceptionManager;
    public void AddClient(Integer idc, Integer ida)throws ExceptionManager;
    public Account CheckAccount(Integer idc)throws ExceptionManager;
    public void AccountMovement(Integer ida, Movement m)throws ExceptionManager;
    public HashMap<Integer, Movement> CheckAccount_Movement(Integer ida)throws ExceptionManager;
    
    
}
