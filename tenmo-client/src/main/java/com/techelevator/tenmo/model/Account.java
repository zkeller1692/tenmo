package com.techelevator.tenmo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class Account {

    private long id;
    private BigDecimal balance;
    @JsonProperty("userId")
    private long userId;

//    public Account(long id, BigDecimal balance){
//        this.id = id;
//        this.balance = balance;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
