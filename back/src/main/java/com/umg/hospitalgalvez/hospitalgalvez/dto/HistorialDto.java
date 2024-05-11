package com.umg.hospitalgalvez.hospitalgalvez.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class HistorialDto {

    private Long id_paciente;
    private Timestamp fecha;
    private Long id_detalle;
    private String diagnostico;
    private Long id_factura;
    private Long id_detalle_historial;

}
