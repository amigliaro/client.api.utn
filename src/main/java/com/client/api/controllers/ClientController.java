package com.client.api.controllers;

import com.client.api.models.Client;
import com.client.api.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("/clientes")
    public class ClientController {
        @Autowired
        private ClientService clientService;

        @GetMapping
        public Client saludar(){
            return clientService.saludar();
        }
    }

