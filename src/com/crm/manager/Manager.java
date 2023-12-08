package com.crm.manager;

import com.crm.entities.Client;
import com.crm.service.Service;

import static com.crm.Main.scanner;
import static com.crm.common.ExceptionMessages.INVALID_INT_VALIDATION;
import static com.crm.common.ExceptionMessages.INVALID_STRING_VALIDATION;

public class Manager {
    private final Service service;

    public Manager(Service service) {
        this.service = service;
    }

    public void performAction(String command) {
        int id;
        Client client;
        switch (command) {
            case "1":
                try {
                    this.service.addClient();
                } catch (IllegalArgumentException exception) {
                    System.out.print(exception.getMessage());
                }

                break;
            case "2":
                System.out.println("Enter the id, which you want to update:");
                id = validateId();
                try {
                    this.service.updateClient(id);
                } catch (IllegalArgumentException exception) {
                    System.out.println(exception.getMessage());
                }

                break;
            case "3":
                System.out.println("Enter the id, which you want to delete:");
                id = validateId();
                try {
                    this.service.removeClient(id);
                } catch (IllegalArgumentException exception) {
                    System.out.println(exception.getMessage());
                }

                break;
            case "4":
                this.service.showAllClients();

                break;
            case "5":
                System.out.println("Enter the id, which you want to search:");
                id = validateId();
                client = this.service.getClientById(id);
                if (client == null) {
                    System.out.println(String.format("Client with %d id does not exist!", id));
                } else {
                    System.out.println("-".repeat(120));
                    System.out.println(client);
                    System.out.println("-".repeat(120));
                }

                break;
            case "6":
                System.out.println("Enter the name, which you want to search:");
                String searchingName = validateStringInput();
                client = this.service.clientByName(searchingName);
                if (client == null) {
                    System.out.println(String.format("Client with name %s does not exist!", searchingName));
                } else {
                    System.out.println("-".repeat(120));
                    System.out.println(client);
                    System.out.println("-".repeat(120));
                }

                break;
            case "7":
                System.out.println("Enter the industry, which you want to search:");
                String searchingIndustry = validateStringInput();
                client = this.service.clientByIndustry(searchingIndustry);
                if (client == null) {
                    System.out.println(String.format("Client with  %s industry does not exist!", searchingIndustry));
                } else {
                    System.out.println("-".repeat(120));
                    System.out.println(client);
                    System.out.println("-".repeat(120));
                }

                break;
            case "8":
                this.service.saveClientsData(this.service.getClientList());
                System.out.println("Closing The Application...");
                System.exit(0);

                break;
            default:
                System.out.println("Choose digit between 1 and 8!");

                break;
        }
    }

    private int validateId() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(INVALID_INT_VALIDATION);
            return validateId();
        }

    }

    private String validateStringInput() {
        String inputLine = scanner.nextLine();
        if (!inputLine.matches("[a-zA-Z ]+")) {
            System.out.println(INVALID_STRING_VALIDATION);
            return validateStringInput();
        }

        return inputLine;
    }
}
