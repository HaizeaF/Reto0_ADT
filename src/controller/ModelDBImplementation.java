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
import java.sql.ResultSet;
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
    public void CreateCustomer(Customer c) throws ExceptionManager {
        con = connection.openConnection();
        String insertCustomer = "insert into customer(?,?,?,?,?,?,?,?,?,?)";

        try {
            stmt = con.prepareStatement(insertCustomer);
            stmt.setInt(1, c.getCustomer_id());
            stmt.setString(2, c.getCity());
            stmt.setString(3, c.getEmail());
            stmt.setString(4, c.getFirstName());
            stmt.setString(5, c.getLastName());
            stmt.setString(6, c.getMiddleInitial());
            stmt.setInt(7, c.getPhone());
            stmt.setString(8, c.getState());
            stmt.setString(9, c.getStreet());
            stmt.setInt(10, c.getZip());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            String error = "Error en la conexion con la base de datos";
            ExceptionManager exception = new ExceptionManager(error);
            throw exception;
        } finally {
            try {
                connection.closeConnection(stmt, con);
            } catch (SQLException e) {
                String error = "Error al cerrar la base de datos";
                ExceptionManager exception = new ExceptionManager(error);
                throw exception;
            }
        }

    }

    @Override
    public Customer CheckCustomer(Integer idc) throws ExceptionManager{
        ResultSet rs = null;
        Customer customer = null;
        
        String checkCustomer = "select c.* from customer c where id = ?";
        con = connection.openConnection();
        
        try {
            stmt = con.prepareStatement(checkCustomer);
            stmt.setInt(1, idc);
            rs = stmt.executeQuery();
            
            if(rs.next()){
                customer = new Customer();
                customer.setCustomer_id(idc);
                customer.setCity(rs.getString("city"));
                customer.setEmail(rs.getString("email"));
                customer.setFirstName(rs.getString("firstName"));
                customer.setLastName(rs.getString("lastName"));
                customer.setMiddleInitial(rs.getString("middleinitial"));
                customer.setPhone(rs.getInt("phone"));
                customer.setState(rs.getString("state"));
                customer.setStreet(rs.getString("street"));
                customer.setZip(rs.getInt("zip"));
            }
            
            
        } catch (SQLException ex) {
            String error = "Error en la conexion con la base de datos";
            ExceptionManager exception = new ExceptionManager(error);
            throw exception;
        } finally {
            try {
                connection.closeConnection(stmt, con);
            } catch (SQLException e) {
                String error = "Error al cerrar la base de datos";
                ExceptionManager exception = new ExceptionManager(error);
                throw exception;
            }
        }
        
        return customer;
    }

    @Override
    public void CreateAccount(Account a, Integer idc) throws ExceptionManager{

    }

    @Override
    public void AddClient(Integer idc, Integer ida) throws ExceptionManager{

    }

    @Override
    public Account CheckAccount(Integer idc) throws ExceptionManager{

    }

    @Override
    public void AccountMovement(Integer ida, Movement m) throws ExceptionManager{

    }

    @Override
    public HashMap<Integer, Movement> CheckAccount_Movement(Integer ida) throws ExceptionManager{

    }

}
