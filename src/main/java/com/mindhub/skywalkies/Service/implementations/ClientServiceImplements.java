package com.mindhub.skywalkies.Service.implementations;

import com.mindhub.skywalkies.Service.ClientService;
import com.mindhub.skywalkies.email.VerificationEmail;
import com.mindhub.skywalkies.models.Client;
import com.mindhub.skywalkies.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImplements implements ClientService {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    VerificationEmail verificationEmail;

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(long id) {
        return clientRepository.findById(id).get();
    }

    @Override
    public Client findClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    @Override
    public void saveClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public void sendVerificationMail(String email, String subject, String body) {
        verificationEmail.sendVerificationMail(email, subject, body);
    }
}
