package com.techelevator.tenmo.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NotEnoughFundsException extends Exception{

    public NotEnoughFundsException() {
    }

    public NotEnoughFundsException(String message) {
        super(message);
    }
}
