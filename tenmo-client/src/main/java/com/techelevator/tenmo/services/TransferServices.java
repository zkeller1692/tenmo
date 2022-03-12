package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.util.BasicLogger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class TransferServices {

    private final String baseUrl;
    private final RestTemplate restTemplate = new RestTemplate();
    AuthenticatedUser currentUser = new AuthenticatedUser();

    public TransferServices(String url) {
        this.baseUrl = url;
    }

    public Transfer[] getAllTransfers(AuthenticatedUser currentUser){
        Transfer[] transfers = null;
        try {
            ResponseEntity<Transfer[]> response = restTemplate.exchange(baseUrl + "users/transfers",
                    HttpMethod.GET,makeAuthEntityForUser(currentUser), Transfer[].class);
            transfers = response.getBody();
        }catch (RestClientResponseException e) {
            BasicLogger.log(e.getRawStatusCode() + " : " + e.getStatusText());
        } catch (ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        } return transfers;
    }

    public Transfer getDetailedTransfer(AuthenticatedUser currentUser, String transferId) {
        Transfer transfer = new Transfer();
        try {
            ResponseEntity<Transfer> response = restTemplate.exchange(baseUrl + "users/transfers/" + transferId,
                    HttpMethod.GET, makeAuthEntityForUser(currentUser), Transfer.class);
            transfer = response.getBody();
        } catch (RestClientResponseException e) {
            BasicLogger.log(e.getRawStatusCode() + " : " + e.getStatusText());
        } catch (ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        } return transfer;
    }

    private HttpEntity<Void> makeAuthEntityForUser(AuthenticatedUser authenticatedUser) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authenticatedUser.getToken());
        return new HttpEntity<>(headers);
    }
}
