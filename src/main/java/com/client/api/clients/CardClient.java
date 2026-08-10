package com.client.api.clients;

import com.client.api.dto.Card;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="cards-app", url = "${card.api.url}")
public interface CardClient {
    @GetMapping("/tarjetas/cliente/{idCliente}")
    Card obtenerProductos(Long idCliente);
}
