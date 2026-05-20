package com.client.api.services;

import com.client.api.dto.Dolar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class DolarService {

    @Value("${dolar.api.url}")
    private String dolarurl;

    private final RestClient restClient = RestClient.create();

    public Dolar obtenerDolarOficial() {

        return restClient.get()
                .uri(dolarurl)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(Dolar.class);
    }

}