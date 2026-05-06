package com.client.api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String tipoDocumento;
    private String direccion;
    private String telefono;
    private String email;
    private LocalDate fechaAlta;

    public boolean esClienteReciente(int diasUmbral) {
        if (fechaAlta == null) return false;
        return !fechaAlta.isBefore(LocalDate.now().minusDays(diasUmbral));
    }
}
