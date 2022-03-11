package com.techelevator.tenmo.services;


import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.User;
import com.techelevator.util.BasicLogger;
import io.cucumber.java.bs.A;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;


public class AccountServices {

    private final String baseUrl;
    private final RestTemplate restTemplate = new RestTemplate();
    AuthenticatedUser currentUser = new AuthenticatedUser();

    public AccountServices(String url) {
        this.baseUrl = url;
    }

    public User[] listUsers(AuthenticatedUser currentUser) {
        User[] users = null;
        try {
            ResponseEntity<User[]> response = restTemplate.exchange(baseUrl + "users", HttpMethod.GET, makeAuthEntityForUser(currentUser), User[].class);
            users = response.getBody();
        } catch (RestClientResponseException e) {
            BasicLogger.log(e.getRawStatusCode() + " : " + e.getStatusText());
        } catch (ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return users;
    }

    public BigDecimal getUserBalance(String username) {
        BigDecimal balance = new BigDecimal("0");
        try {
            balance = restTemplate.getForObject(baseUrl + "users/balance", BigDecimal.class);
        } catch (RestClientResponseException e) {
            BasicLogger.log(e.getRawStatusCode() + " : " + e.getStatusText());
        } catch (ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return balance;
    }

    public boolean transferBalance(BigDecimal transferAmt, String destinUsername) {
        Account origAccount = new Account();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Account> origEnt = new HttpEntity<>(origAccount, headers);
        boolean success = false;
        try {
            restTemplate.put(baseUrl + "users/transfers/" + transferAmt + "/" +  destinUsername, origEnt);
            success = true;
        } catch (RestClientResponseException e) {
            BasicLogger.log(e.getRawStatusCode() + " : " + e.getStatusText());
        } catch (ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return success;
    }

    private HttpEntity<Void> makeAuthEntityForUser(AuthenticatedUser authenticatedUser) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authenticatedUser.getToken());
        return new HttpEntity<>(headers);
    }
}
