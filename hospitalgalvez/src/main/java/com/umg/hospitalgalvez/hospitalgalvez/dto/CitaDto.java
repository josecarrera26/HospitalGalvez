package com.umg.hospitalgalvez.hospitalgalvez.dto;

import com.umg.hospitalgalvez.hospitalgalvez.entity.Paciente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaDto {
    private Long id_cita;
    private Paciente paciente;
    private Timestamp fecha_cita;
    private String descripcion;
    private Long id_usuario;
    private Long id_medico;
    private String estado;
}
