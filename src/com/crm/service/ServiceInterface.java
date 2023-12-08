package com.crm.service;

import com.crm.entities.Client;

import java.util.List;

public interface ServiceInterface {
    void addClient();

    void updateClient(int clientId);

    void removeClient(int clientId);

    boolean containsId(int id);

    void showAllClients();

    Client getClientById(int id);

    Client clientByName(String name);

    Client clientByIndustry(String industry);

    List<Client> loadClientsData(String dbPath);

    void saveClientsData(List<Client> clientList);
}
