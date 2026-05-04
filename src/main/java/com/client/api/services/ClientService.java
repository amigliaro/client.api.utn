package com.client.api.services;

import com.client.api.models.Client;
import com.client.api.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getCliente() {
        return clientRepository.findAll();
    }
}
