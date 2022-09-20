package model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Julen
 */
public class Movement implements Serializable{
    //Atributtes
    private Integer movement_id;
    private LocalDate timeStamp;
    private Float amount;
    private Float balance;
    private String description;
    
    //Constructors
    public Movement() {
    }

    public Movement(Integer movement_id, LocalDate timeStamp, Float amount, Float balance, String description) {
        this.movement_id = movement_id;
        this.timeStamp = timeStamp;
        this.amount = amount;
        this.balance = balance;
        this.description = description;
    }
    
    //getter and setters
    public Integer getMovement_id() {
        return movement_id;
    }

    public void setMovement_id(Integer movement_id) {
        this.movement_id = movement_id;
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDate timeStamp) {
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
}
