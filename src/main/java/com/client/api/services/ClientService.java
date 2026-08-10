package com.client.api.services;

import com.client.api.clients.CardClient;
import com.client.api.dto.Card;
import com.client.api.dto.ClientDTO;
import com.client.api.exceptions.InternalServerErrorException;
import com.client.api.exceptions.NotFoundException;
import com.client.api.mappers.ClientMapper;
import com.client.api.models.Client;
import com.client.api.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final CardClient cardClient;

    public ClientService(ClientRepository clientRepository, CardClient cardClient) {
        this.clientRepository = clientRepository;
        this.cardClient = cardClient;
    }


    public List<ClientDTO> getCliente() {
        try {

            return ClientMapper.clientToDTOList(clientRepository.findAll());
        } catch (InternalServerErrorException ex) {
            throw new InternalServerErrorException("Error al listar los clientes: " + ex.getMessage());
        }
    }

    public ClientDTO getClienteById(Long id) throws NotFoundException {
        Optional<Client> auxCliente = clientRepository.findById(id);
        if (auxCliente.isPresent()) {
            return ClientMapper.clientToDTO(auxCliente.get());
        } else {
            throw new NotFoundException("No se encontró el cliente solicitado");
        }
    }

    public ClientDTO insertClient(Client client) {
        try {
            return ClientMapper.clientToDTO(clientRepository.save(client));
        } catch (InternalServerErrorException ex) {
            throw new InternalServerErrorException("Error al insertar un cliente: " + ex.getMessage());
        }
    }

    public ClientDTO updateClient(Long idCliente, Client client) {
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
            return ClientMapper.clientToDTO(clientRepository.save(auxClient.get()));
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

    public Card getProductosById(Long idCliente) {
        return  cardClient.obtenerProductos(idCliente);
    }
}
