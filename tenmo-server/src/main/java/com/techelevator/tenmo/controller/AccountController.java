package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.security.jwt.BalanceNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

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

@RequestMapping(path = "/users/{username}/balance", method = RequestMethod.GET)
    public BigDecimal getBalance(@PathVariable String username){
    return accountDao.getBalanceByUsername(username);
}
@RequestMapping(path = "/users/{origUsername}/transfers/{transferAmount}/{destinUsername}", method = RequestMethod.PUT)
    public void transferBalance(@PathVariable  String origUsername,@PathVariable BigDecimal transferAmount,
                                @PathVariable String destinUsername) {
    accountDao.transferBalance(transferAmount, origUsername,destinUsername);
}
}
