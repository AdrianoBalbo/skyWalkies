package com.mindhub.skywalkies.Service;

import com.mindhub.skywalkies.models.Client;

import java.util.List;

public interface ClientService {
    public List<Client> getAllClients();

    public Client getClientById(long id);

    public Client findClientByEmail(String email);

    public void saveClient(Client client);
    public void sendVerificationMail(String email, String subject, String body);
}
