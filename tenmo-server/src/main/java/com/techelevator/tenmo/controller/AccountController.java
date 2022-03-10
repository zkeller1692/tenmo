package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
