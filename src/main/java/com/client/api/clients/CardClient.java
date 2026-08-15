package com.client.api.clients;

import com.client.api.dto.Card;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="card.api")
public interface CardClient {
    @GetMapping("/tarjetas/cliente/{idCliente}")
    List<Card> obtenerProductos(@PathVariable Long idCliente);
}
