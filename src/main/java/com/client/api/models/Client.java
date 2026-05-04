package com.client.api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@AllArgsConstructor
public class Client {

    @Id
    private Long id;
    private String nombre;
    private String apellido;
    private String tipoDocumento;
    private String direccion;
    private String telefono;
    private String email;
    private LocalDate fechaAlta;
}
