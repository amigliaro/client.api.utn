package com.client.api.mappers;

import com.client.api.dto.ClientDTO;
import com.client.api.models.Client;

import java.util.List;
import java.util.stream.Collectors;

public class ClientMapper {
    public static ClientDTO clientToDTO(Client client) {
        if (client == null) return null;
        return ClientDTO.builder()
                .nombre(client.getNombre())
                .apellido(client.getApellido())
                .tipoDocumento(client.getTipoDocumento())
                .direccion(client.getDireccion())
                .telefono(client.getTelefono())
                .email(client.getEmail())
                .build();
    }

    public static List<ClientDTO> clientToDTOList(List<Client> clients) {
        return clients.stream()
                .map(ClientMapper::clientToDTO)
                .collect(Collectors.toList());
    }

    public static Client DTOToClient(ClientDTO dto) {
        if (dto == null) return null;
        return Client.builder()
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .tipoDocumento(dto.getTipoDocumento())
                .direccion(dto.getDireccion())
                .telefono(dto.getTelefono())
                .email(dto.getEmail())
                .build();
    }

}
