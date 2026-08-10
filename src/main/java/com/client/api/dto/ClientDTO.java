package com.client.api.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDTO {
    private String nombre;
    private String apellido;
    private String tipoDocumento;
    private String direccion;
    private String telefono;
    private String email;
}
