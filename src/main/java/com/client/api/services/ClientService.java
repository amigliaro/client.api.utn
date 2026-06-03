package com.client.api.services;

import com.client.api.exceptions.CustomException;
import com.client.api.models.Client;
import com.client.api.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public List<Client> getCliente() {
        return clientRepository.findAll();
    }

    public Client getClienteById(Long id) throws CustomException {
        if (clientRepository.findById(id).isPresent()) {
            return clientRepository.findById(id).get();
        } else {
            throw new CustomException("No se encontró el cliente solicitado");
        }
    }

    public Client insertClient(Client client) {
        try {
            return clientRepository.save(client);
        } catch (Exception ex) {
            throw new CustomException(ex.getMessage());
        }
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
