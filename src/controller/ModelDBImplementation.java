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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.AccountType;
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
    public void createCustomer(Customer c) throws ExceptionManager {
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
    public Customer checkCustomer(Integer idc) throws ExceptionManager{
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
    public void createAccount(Account a, Integer idc) throws ExceptionManager{
        con = connection.openConnection();
        String insertAccount = "insert into account(?,?,?,?,?,?,?)";
        
        try {
            stmt = con.prepareStatement(insertAccount);
            stmt.setInt(1, a.getAccount_id());
            stmt.setFloat(2, a.getBalance());
            stmt.setFloat(3, a.getBeginBalance());
            Timestamp ts = Timestamp.valueOf(a.getBeginBalanceTimestamp());
            stmt.setTimestamp(4, ts);
            stmt.setFloat(5, a.getCreditLine());
            stmt.setString(6, a.getDescription());
            stmt.setInt(7, Integer.valueOf(String.valueOf(a.getType())));
            stmt.executeUpdate();
        } catch (SQLException ex) {
           String error = "Error en la conexion con la base de datos";
            ExceptionManager exception = new ExceptionManager(error);
            throw exception;
        } finally {
            try {
                String insertCustomerAccount = "insert into customer_account(?,?)";
                stmt = con.prepareStatement(insertCustomerAccount);
                stmt.setInt(1, a.getAccount_id());
                stmt.setInt(2, idc);
                connection.closeConnection(stmt, con); 
            } catch (SQLException e) {
                String error = "Error al cerrar la base de datos";
                ExceptionManager exception = new ExceptionManager(error);
                throw exception;
            }
        }
    }

    @Override
    public void addCustomer(Integer idc, Integer ida) throws ExceptionManager{
        con = connection.openConnection();
        String addClient = "insert into customer_account values (?,?)";
        
        try {
            stmt = con.prepareStatement(addClient);
            stmt.setInt(1, idc);
            stmt.setInt(2, ida);
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
    public HashMap<Integer, Account> checkAccounts(Integer idc) throws ExceptionManager{
        ResultSet rs = null;
        Account account = null;
        HashMap<Integer, Account> accounts = new HashMap<>();
        
        con = connection.openConnection();
        String checkAccount = "SELECT a.* FROM account a, customer_account ca WHERE a.id=ca.accounts_id and ca.customers_id=?";
        
        try {
            stmt = con.prepareStatement(checkAccount);
            stmt.setInt(1, idc);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                account = new Account();
                account.setAccount_id(rs.getInt("id"));
                account.setDescription(rs.getString("description"));
                account.setBalance(rs.getFloat("balance"));
                account.setCreditLine(rs.getFloat("creditLine"));
                account.setBeginBalance(rs.getFloat("beginBalance"));
                account.setBeginBalanceTimestamp(LocalDateTime.parse((CharSequence) rs.getTimestamp("beginBalanceTimesamp")));
                account.setType(AccountType.valueOf(rs.getString("type")));
                accounts.put(account.getAccount_id(), account);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelDBImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return accounts;
    }

    @Override
    public void accountMovement(Integer ida, Movement m) throws ExceptionManager{
        con = connection.openConnection();
        String insertMovement = "insert into movement(?,?,?,?,?,?)";
        
        try {
            stmt = con.prepareStatement(insertMovement);
            stmt.setInt(1, m.getMovement_id());
            stmt.setFloat(2, m.getAmount());
            stmt.setFloat(3, m.getBalance());
            stmt.setString(4, m.getDescription());
            Timestamp ts = Timestamp.valueOf(m.getTimeStamp());
            stmt.setTimestamp(5, ts);
            stmt.setInt(6, m.getIdAccount());
        } catch (SQLException ex) {
            String error = "Error en la conexion con la base de datos";
            ExceptionManager exception = new ExceptionManager(error);
            throw exception;
        } finally {
            try {
                connection.closeConnection(stmt, con);
            } catch (SQLException ex) {
                String error = "Error al cerrar la base de datos";
                ExceptionManager exception = new ExceptionManager(error);
                throw exception;
            }
        }
    }

    @Override
    public HashMap<Integer, Movement> checkAccount_Movement(Integer ida) throws ExceptionManager{
        HashMap<Integer, Movement> movements = new HashMap<>();
        Movement mv = null;
        ResultSet rs = null;
        
        String checkMovements = "select m.* from account a, movement m where a.id=m.account_id and a.id=?";
        con = connection.openConnection();
        
        try {
            stmt = con.prepareStatement(checkMovements);
            stmt.setInt(1, ida);
            rs = stmt.executeQuery();
            
            while (rs.next()){
                mv = new Movement();
                mv.setMovement_id(rs.getInt("id"));
                mv.setTimeStamp(LocalDateTime.parse((CharSequence) rs.getTimestamp("timestamp")));
                mv.setAmount(rs.getFloat("amunt"));
                mv.setBalance(rs.getFloat("balance"));
                mv.setDescription(rs.getString("description"));
                mv.setIdAccount(rs.getInt("account_id"));
                movements.put(mv.getMovement_id(), mv);
            }
        } catch (SQLException ex) {
            String error = "Error en la conexion con la base de datos";
            ExceptionManager exception = new ExceptionManager(error);
            throw exception;
        } finally {
            try {
                connection.closeConnection(stmt, con);
            } catch (SQLException ex) {
                String error = "Error al cerrar la base de datos";
                ExceptionManager exception = new ExceptionManager(error);
                throw exception;
            }
        }
        return movements;
    }

    @Override
    public Account checkAccount(Integer ida) throws ExceptionManager {
        Account account = null;
        ResultSet rs = null;
        
        String checkAccount = "SELECT * FROM account a where id=?";
        con = connection.openConnection();
        
        try {
            stmt = con.prepareStatement(checkAccount);
            stmt.setInt(1, ida);
            rs = stmt.executeQuery();
            
            if (rs.next()){
                account = new Account();
                account.setAccount_id(rs.getInt("id"));
                account.setDescription(rs.getString("description"));
                account.setBalance(rs.getFloat("balance"));
                account.setCreditLine(rs.getFloat("creditLine"));
                account.setBeginBalance(rs.getFloat("beginBalance"));
                account.setBeginBalanceTimestamp(LocalDateTime.parse((CharSequence) rs.getTimestamp("beginBalanceTimesamp")));
                account.setType(AccountType.valueOf(rs.getString("type")));
            }
        } catch (SQLException ex) {
            String error = "Error en la conexion con la base de datos";
            ExceptionManager exception = new ExceptionManager(error);
            throw exception;
        } finally {
            try {
                connection.closeConnection(stmt, con);
            } catch (SQLException ex) {
                String error = "Error al cerrar la base de datos";
                ExceptionManager exception = new ExceptionManager(error);
                throw exception;
            }
        }
        return account;
    }

}
