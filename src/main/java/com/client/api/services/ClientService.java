package com.client.api.services;

import com.client.api.models.Client;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ClientService {

    public Client saludar() {
        return new Client(1L,"Andres", "Migliaro","DNI","Cabildo 1111","","", LocalDate.now());
    }
}
