package com.crm;

import com.crm.manager.Manager;
import com.crm.service.Service;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Service service = new Service();
        Manager manager = new Manager(service);
        introMessage();
        displayOptions();
        boolean active = true;
        while (active) {
            System.out.println("Insert a command:");
            String command = scanner.nextLine();
            manager.performAction(command);
        }

    }

    private static void displayOptions() {
        StringBuilder mainMenu = new StringBuilder();
        mainMenu.append("-".repeat(103)).append(System.lineSeparator());
        mainMenu.append("Choose your option and enter a digit!").append(System.lineSeparator());
        mainMenu.append("1.Add Client").append(System.lineSeparator());
        mainMenu.append("2.Update Client").append(System.lineSeparator());
        mainMenu.append("3.Remove Client").append(System.lineSeparator());
        mainMenu.append("4.Show all Clients").append(System.lineSeparator());
        mainMenu.append("5.Search Client By Id").append(System.lineSeparator());
        mainMenu.append("6.Search Client By Name").append(System.lineSeparator());
        mainMenu.append("7.Search Client By Industry").append(System.lineSeparator());
        mainMenu.append("8.Close Application").append(System.lineSeparator());
        mainMenu.append("-".repeat(103)).append(System.lineSeparator());
        System.out.println(mainMenu);
    }

    private static void introMessage() {
        System.out.println(System.lineSeparator() + "Welcome to the Client Management System");
        System.out.println("To achieve better user experience and to avoid the boring writing of commands, ");
        System.out.println("our company decided to present the functionality of each command with the number that stands before it.");
    }
}