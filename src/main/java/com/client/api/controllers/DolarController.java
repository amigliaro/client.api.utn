package com.client.api.controllers;

import com.client.api.clients.DolarClient;
import com.client.api.dto.Dolar;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dolar")
public class DolarController {

    private final DolarClient dolarClient;

    public DolarController(DolarClient dolarClient) {
        this.dolarClient = dolarClient;
    }

    @GetMapping("/cotizacion")
    public Dolar getDolarOficial() {
        return dolarClient.obtenerDolarOficial();
    }
}
