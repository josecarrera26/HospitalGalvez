package com.umg.hospitalgalvez.hospitalgalvez.dto;

import java.util.Date;

import com.umg.hospitalgalvez.hospitalgalvez.entity.Especialidad;

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
    private Especialidad especialidad;
    private Long id_usuario;
    private String telefono;
    private String jornada;
    private Integer cod_jefe_inmediato;

    // Campos no persistentes
    private String colegiado;
    private Date fechaCreacion;
    private String direccion;
    private String centro_hospitalario;
    private int edad;
    private String Observaciones;
}