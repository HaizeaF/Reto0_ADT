/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import exception.ExceptionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Customer;
import model.Movement;

/**
 *
 * @author 2dam
 */
public class ModelDBImplementation implements ModelInterface {

    private Connection con;
    private PreparedStatement stmt;
    private ConnectionOpenClose connection = new ConnectionOpenClose();
    
    @Override
    public void CreateCustomer(Customer c) throws ExceptionManager{
        con = connection.openConnection(); 
        String insertCustomer = "insert into customer(?,?,?,?,?,?,?,?,?,?)";
        
        try {
            stmt = con.prepareStatement(insertCustomer);
        } catch (SQLException ex) {
            Logger.getLogger(ModelDBImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public Customer CheckCustomer(Integer idc) {
        
    }

    @Override
    public void CreateAccount(Account a, Integer idc) {
        
    }

    @Override
    public void AddClient(Integer idc, Integer ida) {
        
    }

    @Override
    public Account CheckAccount(Integer idc) {
        
    }

    @Override
    public void AccountMovement(Integer ida, Movement m) {
        
    }

    @Override
    public HashMap<Integer, Movement> CheckAccount_Movement(Integer ida) {
        
    }
    
}
