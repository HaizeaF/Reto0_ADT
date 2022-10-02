package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Julen
 */
public class Movement implements Serializable{
    private static final long serialVersionUID = 1L;
    //Atributtes
    private Integer movement_id;
    private LocalDateTime timeStamp;
    private Float amount;
    private Float balance;
    private String description;
    private Integer idAccount;
    
    //Constructors
    public Movement() {
    }

    public Movement(Integer movement_id, LocalDateTime timeStamp, Float amount, Float balance, String description, Integer idAccount) {
        this.movement_id = movement_id;
        this.timeStamp = timeStamp;
        this.amount = amount;
        this.balance = balance;
        this.description = description;
        this.idAccount = idAccount;
    }
    
    //getter and setters
    public Integer getMovement_id() {
        return movement_id;
    }

    public void setMovement_id(Integer movement_id) {
        this.movement_id = movement_id;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Integer idAccount) {
        this.idAccount = idAccount;
    }
}
