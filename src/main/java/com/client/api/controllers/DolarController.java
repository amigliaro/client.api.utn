package com.client.api.controllers;

import com.client.api.dto.Dolar;
import com.client.api.services.DolarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dolar")
public class DolarController {

    private final DolarService dolarService;

    public DolarController(DolarService dolarService) {
        this.dolarService = dolarService;
    }

    @GetMapping("/cotizacion")
    public Dolar getDolarOficial() {
        return dolarService.obtenerDolarOficial();
    }
}
