package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;

public interface TransferDao {

    Transfer getTransfer(Long transferId);

    void addTransfer(String origUsername, String destinUsername, BigDecimal transferAmt);






}
