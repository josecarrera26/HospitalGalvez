package com.umg.hospitalgalvez.hospitalgalvez.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Paciente;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaDto {
    private Long id_cita;
    private Long id_paciente;
    private Timestamp fecha_cita;
    private String descripcion;
    private Long id_usuario;
    private Long id_medico;
    private String estado;
}
