package com.client.api.services;

import com.client.api.dto.Dolar;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class DolarService {

    private final RestClient restClient = RestClient.create("https://dolarapi.com");

    public Dolar obtenerDolarOficial() {

        return restClient.get()
                .uri("/v1/dolares/oficial")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(Dolar.class);
    }

}