package com.umg.hospitalgalvez.hospitalgalvez.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class HistorialDto {

    private Long id_paciente;
    private String diagnostico;
    private Long id_factura;

}
