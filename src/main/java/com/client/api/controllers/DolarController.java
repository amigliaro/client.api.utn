package com.client.api.controllers;

import com.client.api.dto.Dolar;
import com.client.api.services.DolarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dolar")
public class DolarController {

    @Autowired
    private DolarService dolarService;

    @GetMapping("/cotizacion")
    public Dolar getDolarOficial() {
        return dolarService.obtenerDolarOficial();
    }
}
