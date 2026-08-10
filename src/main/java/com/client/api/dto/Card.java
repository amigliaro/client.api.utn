package com.client.api.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Card {
    private String marca;
    private String tipoTarjeta;
    private String nroTarjeta;
    private String fechaVencimiento;
    private Double limiteCredito;
}
