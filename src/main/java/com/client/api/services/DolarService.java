package com.client.api.services;

import com.client.api.dto.DolarDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class DolarService {

    private final WebClient webClient = WebClient.create("https://dolarapi.com");

    public DolarDTO obtenerDolarOficial() {

        return webClient.get()
                .uri("/v1/dolares/oficial")
                .retrieve()
                .bodyToMono(DolarDTO.class)
                .block();
    }

}