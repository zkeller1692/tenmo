package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.security.jwt.BalanceNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
public class AccountController {

    public UserDao userDao;
    public AccountDao accountDao;
    public TransferDao transferDao;

    public AccountController(UserDao userDao, AccountDao accountDao, TransferDao transferDao) {
        this.userDao = userDao;
        this.accountDao = accountDao;
        this.transferDao = transferDao;
    }

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<User> list() {
        return userDao.findAll();
    }

    @RequestMapping(path = "/users/balance", method = RequestMethod.GET)
    public BigDecimal getBalance(Principal principal) {
        return accountDao.getBalanceByUsername(principal.getName());
    }

    @RequestMapping(path = "/users/transfers/{transferAmount}/{destinUsername}", method = RequestMethod.PUT)
    public void transferBalance(Principal principal, @PathVariable BigDecimal transferAmount,
                                @PathVariable String destinUsername) {
        accountDao.transferBalance(transferAmount, principal.getName(), destinUsername);
        transferDao.addTransfer(principal.getName(), destinUsername, transferAmount);
    }

    @RequestMapping(path = "/users/transfers/{transferId}", method = RequestMethod.GET)
    public Transfer getTransferById(Principal principal, @PathVariable Long transferId) {
        return transferDao.getTransfer(transferId);
    }

    @RequestMapping(path = "/users/transfers", method = RequestMethod.GET)
    public List<Transfer> getTransfersByUsername(Principal principal){
        return transferDao.listTransfersByUsername(principal.getName());
    }
}
