package com.crm.service;

import com.crm.entities.Client;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.crm.common.ExceptionMessages.CLIENT_WITH_ID_ALREADY_EXISTS;
import static com.crm.common.ExceptionMessages.CLIENT_WITH_ID_NOT_EXISTS;
import static com.crm.common.OutputMessages.*;

public class Service implements ServiceInterface {
    private final String DB_PATH = "src/resources/database.csv";
    private final ValidationService validationService = new ValidationService();
    private final List<Client> clientList;

    public Service() {
        this.clientList = loadClientsData(DB_PATH);
    }

    @Override
    public void addClient() {
        Client client = validateClient();
        clientList.add(client);
        System.out.println(String.format(SUCCESSFULLY_ADDED_CLIENT, client.getName()));

    }

    @Override
    public void updateClient(int clientId) {
        if (!containsId(clientId)) {
            throw new IllegalArgumentException(String.format(CLIENT_WITH_ID_NOT_EXISTS,
                    clientId));
        }

        Client oldClient = getClientById(clientId);
        int indexToUpdate = clientList.indexOf(oldClient);
        Client updatedClient = validateClient();
        clientList.set(indexToUpdate, updatedClient);
        System.out.println(String.format(SUCCESSFULLY_UPDATED_CLIENT, clientId));
    }

    @Override
    public void removeClient(int clientId) {
        if (!containsId(clientId)) {
            throw new IllegalArgumentException(String.format(CLIENT_WITH_ID_NOT_EXISTS,
                    clientId));
        }

        clientList.remove(getClientById(clientId));
        System.out.println(String.format(SUCCESSFULLY_REMOVED_CLIENT, clientId));
    }

    @Override
    public boolean containsId(int id) {
        Client client = clientList.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);

        return client != null;
    }

    @Override
    public void showAllClients() {
        if (!clientList.isEmpty()) {
            System.out.println(ALL_CURRENT_CLIENTS);
            System.out.println("-".repeat(120));
            clientList.forEach(System.out::println);
            System.out.println("-".repeat(120));
        } else {
            System.out.println(EMPTY_CURRENT_CLIENTS_LIST);
        }

    }

    @Override
    public Client getClientById(int id) {
        return this.clientList.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);

    }

    @Override
    public Client clientByName(String name) {
        return clientList.stream()
                .filter(e -> e.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Client clientByIndustry(String industry) {
        return clientList.stream()
                .filter(e -> e.getIndustry().equals(industry))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Client> loadClientsData(String dbPath) {
        List<Client> clients = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(DB_PATH))) {
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                String[] clientsData = inputLine.split(",");
                int id = Integer.parseInt(clientsData[0]);
                String name = clientsData[1];
                String industry = clientsData[2];
                String contactPerson = clientsData[3];
                double revenue = Double.parseDouble(clientsData[4]);
                Client client = new Client(id, name, industry, contactPerson, revenue);
                clients.add(client);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return clients;
    }

    @Override
    public void saveClientsData(List<Client> clientList) {
        StringBuilder sb = new StringBuilder();
        clientList.forEach(e -> sb.append(e.getId() + "," +
                                          e.getName() + "," +
                                          e.getIndustry() + "," +
                                          e.getContactPerson() + "," +
                                          e.getRevenue() +
                                          System.lineSeparator()));

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(DB_PATH))) {
            bufferedWriter.write(sb.toString().trim());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private Client validateClient() {
        System.out.println("Enter id:");
        int id = validationService.validateIntNumber();
        if (containsId(id)) {
            throw new IllegalArgumentException(String.format(CLIENT_WITH_ID_ALREADY_EXISTS,
                    id));
        }

        System.out.println("Enter name:");
        String name = validationService.validateStringInput();
        System.out.println("Enter industry:");
        String industry = validationService.validateStringInput();
        System.out.println("Enter contact person details:");
        String contactPerson = validationService.validateStringInput();
        System.out.println("Enter revenue:");
        double revenue = validationService.validateDoubleNumber();
        return new Client(id, name, industry, contactPerson, revenue);
    }

    public List<Client> getClientList() {
        return Collections.unmodifiableList(clientList);
    }
}
