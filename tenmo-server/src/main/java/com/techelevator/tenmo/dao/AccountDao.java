package com.techelevator.tenmo.dao;

import org.springframework.security.access.prepost.PreAuthorize;

import java.math.BigDecimal;
import java.security.Principal;

public interface AccountDao {

    BigDecimal getBalanceByUsername(String username);
    void transferBalance(BigDecimal transferAmt, Long sourceUserId, Long destinUserId);

    void transferBalance(BigDecimal transferAmt, String origUsername, String destinUsername);

    BigDecimal getBalanceById(Long id);
}

