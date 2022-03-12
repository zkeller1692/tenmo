package com.techelevator.tenmo;

import com.techelevator.tenmo.model.*;
import com.techelevator.tenmo.services.AccountServices;
import com.techelevator.tenmo.services.AuthenticationService;
import com.techelevator.tenmo.services.ConsoleService;
import com.techelevator.tenmo.services.TransferServices;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Scanner;

public class App {

    private static final String API_BASE_URL = "http://localhost:8080/";

    private final ConsoleService consoleService = new ConsoleService();
    private final AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);
    private final AccountServices accountServices= new AccountServices(API_BASE_URL);
    private final TransferServices transferServices = new TransferServices(API_BASE_URL);
    private AuthenticatedUser currentUser;

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        consoleService.printGreeting();
        loginMenu();
        if (currentUser != null) {
            mainMenu();
        }
    }
    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != 0 && currentUser == null) {
            consoleService.printLoginMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                handleRegister();
            } else if (menuSelection == 2) {
                handleLogin();
            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection");
                consoleService.pause();
            }
        }
    }

    private void handleRegister() {
        System.out.println("Please register a new user account");
        UserCredentials credentials = consoleService.promptForCredentials();
        if (authenticationService.register(credentials)) {
            System.out.println("Registration successful. You can now login.");
        } else {
            consoleService.printErrorMessage();
        }
    }

    private void handleLogin() {
        UserCredentials credentials = consoleService.promptForCredentials();
        currentUser = authenticationService.login(credentials);
        if (currentUser == null) {
            consoleService.printErrorMessage();
        }
    }

    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                viewCurrentBalance();
            } else if (menuSelection == 2) {
                viewTransferHistory();
            } else if (menuSelection == 3) {
                viewPendingRequests();
            } else if (menuSelection == 4) {
                sendBucks();
            } else if (menuSelection == 5) {
                requestBucks();
            } else if (menuSelection == 0) {
                continue;
            } else {
                System.out.println("Invalid Selection");
            }
            consoleService.pause();
        }
    }

	private void viewCurrentBalance() {
        //tring username = currentUser.getUser().getUsername();
        System.out.println("Your current account balance is: $" + accountServices.getUserBalance(currentUser));
	}

	private void viewTransferHistory() {
        Transfer[] transfers = transferServices.getAllTransfers(currentUser);
        for(Transfer transfer : transfers){
            System.out.println(" ----------------------------------");
            System.out.println("Transfers");
            System.out.println(" ----------------------------------");
            System.out.println("Transfer ID: " +transfer.getId());
            System.out.println("Transfer Amount: " + transfer.getTransferAmount());
        }


        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter transfer ID to see more details:");
        String transferId = userInput.nextLine();
        System.out.println(transferServices.getDetailedTransfer(currentUser, transferId).toString());
	}


	private void viewPendingRequests() {
		// TODO Auto-generated method stub
		
	}

	private void sendBucks() {
        User[] users = accountServices.listUsers(currentUser);
        for (User user : users) {
            if( !user.equals(currentUser.getUser()))
            System.out.println(user.getUsername());
        }

        Scanner userInput = new Scanner(System.in);

        System.out.println("Please enter user to send to:");
        String destination = userInput.nextLine();

        System.out.println("Please enter amount you would like to send:");
        String amount = userInput.nextLine();
        BigDecimal amountToDeposit = new BigDecimal(amount);

        if (currentUser.getUser().getUsername().equals(destination)){
            System.out.println("You can't send money to yourself!");
       } else if (amountToDeposit.compareTo(BigDecimal.ZERO) <= 0){
            System.out.println("You can't send negative or 0 amounts!");
        } else if (accountServices.getUserBalance(currentUser).compareTo(BigDecimal.ZERO) < 0) {
            System.out.println("You don't have any money!");
        } else if (accountServices.getUserBalance(currentUser).compareTo(amountToDeposit) < 0) {
            System.out.println("You don't have enough money!");
        }
         else{ accountServices.transferBalance(currentUser, amountToDeposit, destination);
            System.out.println("Transaction Approved!");
        }
    }

	private void requestBucks() {
		// TODO Auto-generated method stub
		
	}

}
