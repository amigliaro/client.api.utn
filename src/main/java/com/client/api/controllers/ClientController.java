package com.client.api.controllers;

import com.client.api.models.Client;
import com.client.api.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/clientes")
    public class ClientController {
        @Autowired
        private ClientService clientService;

        @GetMapping
        public List<Client> listarClientes(){
            return clientService.getCliente();
        }

        @GetMapping("/{idCliente}")
        public Client getClienteById(@PathVariable Long idCliente){
            return clientService.getClienteById(idCliente);
        }

        @PostMapping
        public Client insertCliente(@RequestBody Client client){
           return clientService.insertClient(client);
        }
    }

