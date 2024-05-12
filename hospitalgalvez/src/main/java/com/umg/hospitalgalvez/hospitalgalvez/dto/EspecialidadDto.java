package com.umg.hospitalgalvez.hospitalgalvez.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EspecialidadDto {
    private Long id_especialidad;
    private String nombre_especialidad;
    private String descripcion;
    private String estado;
}