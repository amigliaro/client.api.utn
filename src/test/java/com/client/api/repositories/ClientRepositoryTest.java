package com.client.api.repositories;

import com.client.api.models.Client;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;


    @Test
    void debeGuardarCliente() {
        Client cliente = new Client(6L, "Prueba", "H2", "","","","", LocalDate.of(2026,5,4));
        clientRepository.save(cliente);
        Optional<Client> clienteOptional = clientRepository.findById(cliente.getId());
        assertTrue(clienteOptional.isPresent());
        log.info("Cliente encontrado: {}", clienteOptional.get());
    }

}