package com.techelevator.tenmo.security.jwt;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Balance Not Found")
    public class BalanceNotFoundException extends Exception {
        private static final long serialVersionUID = 1L;

        public BalanceNotFoundException() {
            super("Balance Not Found");
        }
    }
