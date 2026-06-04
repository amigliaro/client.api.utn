package com.client.api.services;

import com.client.api.dto.Dolar;
import com.client.api.exceptions.CustomException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Service
public class DolarService {

    private final RestClient restClient = RestClient.create();
    @Value("${dolar.api.url}")
    private String dolarurl;

    public Dolar obtenerDolarOficial() {
        try {
            return restClient.get().uri(dolarurl).accept(MediaType.APPLICATION_JSON).retrieve().body(Dolar.class);
        } catch (HttpClientErrorException e) {
            throw new CustomException("No se pudo obtener la cotización del dólar");
        }
    }

}