package com.client.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DolarDTO {

    private String moneda;
    private String casa;
    private String nombre;
    private Double compra;
    private Double venta;
    private String fechaActualizacion;

}
