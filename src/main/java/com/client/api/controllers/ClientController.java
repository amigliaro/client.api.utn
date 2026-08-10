package com.client.api.controllers;

import com.client.api.dto.Card;
import com.client.api.dto.ClientDTO;
import com.client.api.exceptions.NotFoundException;
import com.client.api.models.Client;
import com.client.api.services.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/clientes")
    public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientDTO> listarClientes() {
        return clientService.getCliente();
    }

    @GetMapping("/{idCliente}")
    public ClientDTO getClienteById(@PathVariable Long idCliente) throws NotFoundException {
        return clientService.getClienteById(idCliente);
    }

    @PostMapping
    public ClientDTO insertCliente(@RequestBody Client client) {
        return clientService.insertClient(client);
    }

    @PutMapping("/{idCliente}")
    public ClientDTO insertCliente(@PathVariable Long idCliente, @RequestBody Client client) {
        return clientService.updateClient(idCliente, client);
    }

    @DeleteMapping("/{idCliente}")
    public void deleteCliente(@PathVariable Long idCliente) {
        clientService.deleteClient(idCliente);
    }

    @GetMapping("/{idCliente}/products")
    public Card getProductosById(@PathVariable Long idCliente) throws NotFoundException {
        return clientService.getProductosById(idCliente);
    }
}
