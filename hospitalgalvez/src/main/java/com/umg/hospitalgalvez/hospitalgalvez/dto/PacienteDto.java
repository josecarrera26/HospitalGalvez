package com.umg.hospitalgalvez.hospitalgalvez.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PacienteDto {

    private Long id_paciente;
    private String nombre;
    private String apellido;
    private String fecha_nacimiento;
    private String direccion;
    private String telefono;
    private String dpi;
    private String nit;
    private String email;
    private String genero;
    private int id_usuario;
    private String estado;
}
