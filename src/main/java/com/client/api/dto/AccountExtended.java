package com.client.api.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountExtended {
    private Long accountId;

    private String numeroCuenta;
    private String moneda;
    private Double saldo;
    private Double saldoPesos;
    private LocalDateTime fechaModificacion;
}
