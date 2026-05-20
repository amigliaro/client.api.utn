package com.client.api.controllers;

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
    public List<Client> listarClientes() {
        return clientService.getCliente();
    }

    @GetMapping("/{idCliente}")
    public Client getClienteById(@PathVariable Long idCliente) {
        return clientService.getClienteById(idCliente);
    }

    @PostMapping
    public Client insertCliente(@RequestBody Client client) {
        return clientService.insertClient(client);
    }

    @PutMapping("/{idCliente}")
    public Client insertCliente(@PathVariable Long idCliente, @RequestBody Client client) {
        return clientService.updateClient(idCliente, client);
    }

    @DeleteMapping("/{idCliente}")
    public void deleteCliente(@PathVariable Long idCliente) {
        clientService.deleteClient(idCliente);
    }
}
