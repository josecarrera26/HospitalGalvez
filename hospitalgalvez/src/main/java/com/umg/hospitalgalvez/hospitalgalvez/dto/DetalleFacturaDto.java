package com.umg.hospitalgalvez.hospitalgalvez.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleFacturaDto {
    private Long id_factura;
    private Long id_medicamento;
}