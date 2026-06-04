package com.client.api.dto;

import com.client.api.models.Client;
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
    private Client client;

    private Double saldo;
    private Double saldoPesos;
    private LocalDateTime fechaModificacion;
}
