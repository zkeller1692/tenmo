package com.techelevator.tenmo.services;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public class AccountServiceImpl implements AccountService {


    private final AccountDao accountDao;
    private final TransferDao transferDao;

    public AccountServiceImpl(AccountDao accountDao, TransferDao transferDao) {
        this.accountDao = accountDao;
        this.transferDao = transferDao;
    }

    @Transactional
    public boolean transferFunds(Long sourceUser, Long destinationUser, BigDecimal amount) throws NotEnoughFundsException {
        BigDecimal balance = accountDao.getBalanceById(sourceUser);
        if (amount.compareTo(balance) < 0){
            throw new NotEnoughFundsException("You are too poor, sorry.");
        }
        accountDao.transferBalance(amount, sourceUser, destinationUser);
        transferDao.addTransfer(sourceUser, destinationUser, amount);
        return true;
    }
}
