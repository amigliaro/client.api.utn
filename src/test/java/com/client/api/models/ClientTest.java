package com.client.api.models;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Value("${app.clientes.diasumbral}")
    private int diasUmbral;


    @Test
    void esClienteReciente() {
        // generar cliente que cumpla la condición y chequear que de true}
        Client cliente = Client.builder()
                .id(5L)
                .nombre("Diego")
                .apellido("Diaz")
                .tipoDocumento("")
                .direccion("")
                .telefono("")
                .email("")
                .build();
        boolean isOk = cliente.esClienteReciente(diasUmbral);
        assertTrue(isOk);
    }

    @Test
    void noEsClienteReciente() {
        // generar cliente que no cumpla la condición y chequear que de false
        Client cliente = Client.builder()
                .id(5L)
                .nombre("Diego")
                .apellido("Diaz")
                .tipoDocumento("")
                .direccion("")
                .telefono("")
                .email("")
                .build();
        boolean isOk = cliente.esClienteReciente(diasUmbral);
        assertFalse(isOk);
    }
}