package com.umg.hospitalgalvez.hospitalgalvez.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class EspecialidadDto {
    private Long id_especialidad;
    private String nombre_especialidad;
    private String descripcion;
    private String estado;
}