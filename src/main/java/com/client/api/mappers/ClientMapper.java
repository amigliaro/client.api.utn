package com.client.api.mappers;

import com.client.api.dto.ClientDTO;
import com.client.api.models.Client;

import java.util.List;
import java.util.stream.Collectors;

public class ClientMapper {
    public static ClientDTO clientToDTO(Client client) {
        if (client == null) return null;
        return new ClientDTO(client.getNombre(), client.getApellido(), client.getTipoDocumento(), client.getDireccion(), client.getTelefono(), client.getEmail());
    }

    public static List<ClientDTO> clientToDTOList(List<Client> clients) {
        return clients.stream()
                .map(ClientMapper::clientToDTO)
                .collect(Collectors.toList());
    }

}
