/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

/**
 *
 * @author 2dam
 */
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    // <-- Attributes -->
    private Integer account_id;
    private String description;
    private Float balance;
    private Float creditLine;
    private Float beginBalance;
    private LocalDateTime beginBalanceTimestamp;
    private AccountType type;
    private HashMap<Integer,Customer> customers;
    
    // <-- Constructors -->
    public Account() {
        customers = new HashMap<>();
    }

    public Account(Integer account_id, String description, Float balance, Float creditLine, Float beginBalance, LocalDateTime beginBalanceTimestamp, AccountType type, HashMap<Integer, Customer> customers) {
        this.account_id = account_id;
        this.description = description;
        this.balance = balance;
        this.creditLine = creditLine;
        this.beginBalance = beginBalance;
        this.beginBalanceTimestamp = beginBalanceTimestamp;
        this.type = type;
        this.customers = customers;
    }
    
    // <-- Getters and Setters -->
    public Integer getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Float getCreditLine() {
        return creditLine;
    }

    public void setCreditLine(Float creditLine) {
        this.creditLine = creditLine;
    }

    public Float getBeginBalance() {
        return beginBalance;
    }

    public void setBeginBalance(Float beginBalance) {
        this.beginBalance = beginBalance;
    }

    public LocalDateTime getBeginBalanceTimestamp() {
        return beginBalanceTimestamp;
    }

    public void setBeginBalanceTimestamp(LocalDateTime beginBalanceTimestamp) {
        this.beginBalanceTimestamp = beginBalanceTimestamp;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public HashMap<Integer, Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(HashMap<Integer, Customer> customers) {
        this.customers = customers;
    }
    
}
