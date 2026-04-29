package com.client.api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    private Long id;
    private String nombre;
    private String apellido;
    private String tipoDocumento;
    private String direccion;
    private String telefono;
    private String email;
    private LocalDate fechaAlta;

    @Override
    public String toString() {
        return "Cliente [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", tipoDocumento="
                + tipoDocumento + ", direccion=" + direccion + ", telefono=" + telefono + ", email=" + email
                + ", fechaAlta=" + fechaAlta + ",";
    }


}
