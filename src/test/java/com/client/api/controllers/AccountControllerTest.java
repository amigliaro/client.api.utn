package com.client.api.controllers;

import com.client.api.models.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getAccountTest() throws Exception {
        mockMvc.perform(get("/cuentas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].accountId").value(1))
                .andExpect(jsonPath("$[0].numeroCuenta").exists());
    }

    @Test
    void getAccountByIdTest() throws Exception {
        mockMvc.perform(get("/cuentas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountId").value(1))
                .andExpect(jsonPath("$.numeroCuenta").exists());
    }

    @Test
    void getAccountByIdNoEncontradoTest() throws Exception {
        mockMvc.perform(get("/cuentas/999999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("No se encontró la cuenta solicitada."));
    }

    @Test
    void insertAccountTest() throws Exception {
        Long clienteId = 1L;

        Account cuenta = Account.builder()
                .numeroCuenta("1354875643654")
                .moneda("USD")
                .build();

        mockMvc.perform(post("/cuentas/{clienteId}",clienteId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cuenta)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numeroCuenta").value("1354875643654"))
                .andExpect(jsonPath("$.moneda").value("USD"))
                .andExpect(jsonPath("$.saldo").value(0.0))
                .andExpect(jsonPath("$.activo").value(true));
    }

    @Test
    void insertAccountClienteNoEncontradoTest() throws Exception {
        Long clienteId = 99999L;

        Account cuenta = Account.builder()
                .numeroCuenta("1354875643654")
                .moneda("USD")
                .build();

        mockMvc.perform(post("/cuentas/{clienteId}",clienteId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cuenta)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("No se encontró el cliente ingresado para la creación de la cuenta."));
    }

    @Test
    void updateAccountTest() throws Exception {

        Long accountId = 1L;

        Account cuenta = Account.builder()
                .numeroCuenta("1354875643654")
                .moneda("USD")
                .saldo(100.0)
                .activo(true)
                .build();

        mockMvc.perform(put("/cuentas/{accountId}", accountId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cuenta)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numeroCuenta").value("1354875643654"))
                .andExpect(jsonPath("$.moneda").value("USD"))
                .andExpect(jsonPath("$.saldo").value(100.0))
                .andExpect(jsonPath("$.activo").value(true));
    }

    @Test
    void updateAccountIdNoEncontradoTest() throws Exception {
        Long accountId = 99999L;

        Account cuenta = Account.builder()
                .numeroCuenta("1354875643654")
                .moneda("USD")
                .saldo(100.0)
                .activo(true)
                .build();

        mockMvc.perform(put("/cuentas/{accountId}", accountId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cuenta)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("No se encontró la cuenta para el id ingresado."));
    }

    @Test
    void deleteAccountTest() throws Exception {
        Long accountId = 1L;

        mockMvc.perform(delete("/cuentas/{accountId}", accountId))
                .andExpect(status().isOk());
    }

    @Test
    void deleteClientIdNoEncontradoTest() throws Exception {
        Long accountId = 99999L;

        mockMvc.perform(delete("/cuentas/{accountId}", accountId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("No se encontró la cuenta para el id ingresado."));
    }
}
