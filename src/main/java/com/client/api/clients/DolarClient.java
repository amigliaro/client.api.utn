package com.client.api.clients;

import com.client.api.dto.Dolar;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="dolar-app", url = "${dolar.api.url}")
public interface DolarClient {
    @GetMapping("/dolares/oficial")
    Dolar obtenerDolarOficial();
}
