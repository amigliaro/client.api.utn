package com.client.api.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Dolar {

    private String moneda;
    private String casa;
    private String nombre;
    private Double compra;
    private Double venta;
    private String fechaActualizacion;

}
