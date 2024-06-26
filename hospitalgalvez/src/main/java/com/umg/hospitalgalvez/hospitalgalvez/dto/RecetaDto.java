package com.umg.hospitalgalvez.hospitalgalvez.dto;

import java.sql.Date;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecetaDto {
    private Long id_receta;
    private Long id_cita;
    private Long [] id_medicamento;
    private String [] descripcion;
}
