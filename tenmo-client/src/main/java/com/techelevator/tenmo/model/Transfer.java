package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {
    private Long id;
    private Long transferTypeId;
    private Long accountFromId;
    private BigDecimal transferAmount;
    private Long transferStatusId;
    private Long accountToId;


    public Long getId() {
        return id;
    }
    public Long getTransferTypeId() {
        return transferTypeId;
    }
    public Long getAccountFromId() {
        return accountFromId;
    }
    public Long getAccountToId() {
        return accountToId;
    }
    public BigDecimal getTransferAmount() {
        return transferAmount;
    }
    public Long getTransferStatusId() {
        return transferStatusId;
    }

    @Override
    public String toString(){
        return "\n ----------------------------------" +
                "\n Transfer Details: " +
                "\n ----------------------------------" +
                "\n Transfer ID: " + id +
                "\n Transfer Type ID: " + transferTypeId +
                "\n Transfer Status ID " + transferStatusId +
                "\n Account From ID: " + accountFromId +
                "\n Account To ID: " + accountToId +
                "\n Amount: " + transferAmount;
    }

}
