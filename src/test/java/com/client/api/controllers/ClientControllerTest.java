package com.client.api.controllers;

import com.client.api.models.Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

   private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getClienteTest() throws Exception {
        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].clientId").value(1))
                .andExpect(jsonPath("$[0].nombre").exists());
    }

    @Test
    void getClienteByIdTest() throws Exception {
        mockMvc.perform(get("/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientId").value(1))
                .andExpect(jsonPath("$.nombre").exists());
    }

    @Test
    void getClienteByIdNoEncontradoTest() throws Exception {
        mockMvc.perform(get("/clientes/999999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("No se encontró el cliente solicitado"));
    }

    @Test
    void insertClientTest() throws Exception {
        Client cliente = Client.builder()
               //.clientId(99L)
                .nombre("Diego")
                .apellido("Diaz")
                .tipoDocumento("DNI")
                .direccion("Cabildo 1111")
                .telefono("1130129292")
                .email("diego@gmail.com")
                .build();

        mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Diego"))
                .andExpect(jsonPath("$.apellido").value("Diaz"))
                .andExpect(jsonPath("$.tipoDocumento").value("DNI"))
                .andExpect(jsonPath("$.direccion").value("Cabildo 1111"))
                .andExpect(jsonPath("$.telefono").value("1130129292"))
                .andExpect(jsonPath("$.email").value("diego@gmail.com"));
    }

    @Test
    void updateClientTest() throws Exception {

        Long clienteId = 1L;

        Client cliente = Client.builder()
                .nombre("Diego")
                .apellido("Diaz")
                .tipoDocumento("DNI")
                .direccion("Cabildo 1111")
                .telefono("1130129292")
                .email("diego@gmail.com")
                .build();

        mockMvc.perform(put("/clientes/{clienteId}", clienteId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Diego"))
                .andExpect(jsonPath("$.apellido").value("Diaz"))
                .andExpect(jsonPath("$.tipoDocumento").value("DNI"))
                .andExpect(jsonPath("$.direccion").value("Cabildo 1111"))
                .andExpect(jsonPath("$.telefono").value("1130129292"))
                .andExpect(jsonPath("$.email").value("diego@gmail.com"));
    }

    @Test
    void updateClientIdNoEncontradoTest() throws Exception {
        Long clienteId = 99999L;

        Client cliente = Client.builder()
                .nombre("Diego")
                .apellido("Diaz")
                .tipoDocumento("DNI")
                .direccion("Cabildo 1111")
                .telefono("1130129292")
                .email("diego@gmail.com")
                .build();

        mockMvc.perform(put("/clientes/{clienteId}", clienteId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("No se encontró información para el cliente ingresado."));
    }

    @Test
    void deleteClientTest() throws Exception {

        Long clienteId = 1L;

        mockMvc.perform(delete("/clientes/{clienteId}", clienteId))
                .andExpect(status().isOk());
    }

    @Test
    void deleteClientIdNoEncontradoTest() throws Exception {

        Long clienteId = 99999L;

        mockMvc.perform(delete("/clientes/{clienteId}", clienteId))
                .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message").value("No se encontró información para el cliente ingresado."));
    }
}
