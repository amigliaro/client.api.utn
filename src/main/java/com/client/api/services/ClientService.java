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

    public Client getClienteById(Long id) {

        if  (clientRepository.findById(id).isPresent()) {
            return clientRepository.findById(id).get();
        }
        return null;
    }

    public Client insertClient(Client client) {
       return clientRepository.save(client);
    }

    public Client updateClient(Long idCliente, Client client) {
        Client auxClient = clientRepository.getReferenceById(idCliente);

        if (client.getNombre() != null) auxClient.setNombre(client.getNombre());
        if (client.getApellido() != null) auxClient.setApellido(client.getApellido());
        if (client.getEmail() != null) auxClient.setEmail(client.getEmail());
        if (client.getTipoDocumento() != null) auxClient.setTipoDocumento(client.getTipoDocumento());
        if (client.getTelefono() != null) auxClient.setTelefono(client.getTelefono());
        if (client.getDireccion() != null) auxClient.setDireccion(client.getDireccion());

        return clientRepository.save(auxClient);
    }


    public void deleteClient(Long idCliente) {
        clientRepository.deleteById(idCliente);
    }
}
