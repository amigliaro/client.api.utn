package com.client.api.services;

import com.client.api.exceptions.InternalServerErrorException;
import com.client.api.exceptions.NotFoundException;
import com.client.api.models.Client;
import com.client.api.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public List<Client> getCliente() {
        try {

            return clientRepository.findAll();
        } catch (InternalServerErrorException ex) {
            throw new InternalServerErrorException("Error al listar los clientes: " + ex.getMessage());
        }
    }

    public Client getClienteById(Long id) throws NotFoundException {
        Optional<Client> auxCliente = clientRepository.findById(id);
        if (auxCliente.isPresent()) {
            return auxCliente.get();
        } else {
            throw new NotFoundException("No se encontró el cliente solicitado");
        }
    }

    public Client insertClient(Client client) {
        try {
            return clientRepository.save(client);
        } catch (InternalServerErrorException ex) {
            throw new InternalServerErrorException("Error al insertar un cliente: " + ex.getMessage());
        }
    }

    public Client updateClient(Long idCliente, Client client) {
        Optional<Client> auxClient = clientRepository.findById(idCliente);

        if (auxClient.isPresent()) {
            if (client.getNombre() != null) auxClient.get().setNombre(client.getNombre());
            if (client.getApellido() != null) auxClient.get().setApellido(client.getApellido());
            if (client.getEmail() != null) auxClient.get().setEmail(client.getEmail());
            if (client.getTipoDocumento() != null) auxClient.get().setTipoDocumento(client.getTipoDocumento());
            if (client.getTelefono() != null) auxClient.get().setTelefono(client.getTelefono());
            if (client.getDireccion() != null) auxClient.get().setDireccion(client.getDireccion());

        } else {
            throw new NotFoundException("No se encontró información para el cliente ingresado.");
        }
        try {
            return clientRepository.save(auxClient.get());
        } catch (InternalServerErrorException ex) {
            throw new InternalServerErrorException("Error al modificar un cliente: " + ex.getMessage());
        }
    }

    public void deleteClient(Long idCliente) {
        Optional<Client> auxClient = clientRepository.findById(idCliente);
        if (auxClient.isPresent()) {
            try {
                clientRepository.deleteById(idCliente);
            } catch (InternalServerErrorException ex) {
                throw new InternalServerErrorException("Error al eliminar un cliente: " + ex.getMessage());
            }
        } else {
            throw new NotFoundException("No se encontró información para el cliente ingresado.");
        }
    }
}
