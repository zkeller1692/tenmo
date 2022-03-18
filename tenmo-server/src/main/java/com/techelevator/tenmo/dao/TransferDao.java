package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {

    Transfer getTransfer(Long transferId);

    void addTransfer(Long origUserId, Long destinationUserId, BigDecimal transferAmt);
    void addTransfer(String origUsername, String destinUsername, BigDecimal transferAmt);

    List<Transfer> listTransfersByUsername(String username);





}
