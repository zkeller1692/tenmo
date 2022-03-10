package com.techelevator.tenmo.dao;

import java.math.BigDecimal;

public interface AccountDao {

    BigDecimal getBalanceByUsername(String username);

    void transferBalance(BigDecimal transferAmt, String origUsername, String destinUsername);
}

