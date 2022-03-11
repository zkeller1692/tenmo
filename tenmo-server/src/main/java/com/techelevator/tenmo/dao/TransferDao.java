package com.techelevator.tenmo.dao;

import java.math.BigDecimal;

public interface TransferDao {

    void addTransfer(long transferId, long transferTypeId,
                     long accountFromId, long accountToId, BigDecimal transferAmount);


}
