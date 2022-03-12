package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getTransferTypeId() {
        return transferTypeId;
    }
    public void setTransferTypeId(Long transferTypeId) {
        this.transferTypeId = transferTypeId;
    }
    public Long getAccountFromId() {
        return accountFromId;
    }
    public void setAccountFromId(Long accountFromId) {
        this.accountFromId = accountFromId;
    }
    public Long getAccountToId() {
        return accountToId;
    }
    public void setAccountToId(Long accountToId) {
        this.accountToId = accountToId;
    }
    public BigDecimal getTransferAmount() {
        return transferAmount;
    }
    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }

    private Long id;
    private Long transferTypeId;
    private Long accountFromId;
    private Long accountToId;
    private BigDecimal transferAmount;


}
