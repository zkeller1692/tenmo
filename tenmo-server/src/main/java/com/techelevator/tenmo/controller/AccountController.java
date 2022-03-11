package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
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

public AccountController(UserDao userDao, AccountDao accountDao){
    this.userDao = userDao;
    this.accountDao = accountDao;
}

@RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<User> list(){
    return userDao.findAll();
}

@RequestMapping(path = "/users/balance", method = RequestMethod.GET)
    public BigDecimal getBalance(Principal principal){
    return accountDao.getBalanceByUsername(principal.getName());
}
@RequestMapping(path = "/users/transfers/{transferAmount}/{destinUsername}", method = RequestMethod.PUT)
    public void transferBalance(Principal principal, @PathVariable BigDecimal transferAmount,
                                @PathVariable String destinUsername) {
    accountDao.transferBalance(transferAmount, principal.getName() ,destinUsername);
}
}
