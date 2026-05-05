package com.client.api.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void esClienteReciente() {
        // generar cliente que cumpla la condición y chequear que de true}
        Client cliente = new Client(5L, "Diego", "Diaz", "","","","", LocalDate.of(2026,5,4));
        boolean isOk = cliente.esClienteReciente(3);
        assertTrue(isOk);
    }

    @Test
    void noEsClienteReciente() {
        // generar cliente que no cumpla la condición y chequear que de false
        Client cliente = new Client(5L, "Diego", "Diaz", "","","","", LocalDate.of(2026,5,1));
        boolean isOk = cliente.esClienteReciente(3);
        assertFalse(isOk);
    }
}