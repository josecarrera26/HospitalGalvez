package com.umg.hospitalgalvez.hospitalgalvez.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MedicoDto {

    private Long id_medico;
    private String nombre_medico;
    private String apellido_medico;
    private Long id_especialidad;
    private Long id_usuario;
    private String telefono;
    private String jornada;
    private String cod_jefe_inmediato;
}