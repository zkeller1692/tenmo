package com.techelevator.tenmo.services;

import java.math.BigDecimal;

public interface AccountService {
    boolean transferFunds(Long sourceUser, Long destinationUser, BigDecimal amount) throws NotEnoughFundsException;
}
