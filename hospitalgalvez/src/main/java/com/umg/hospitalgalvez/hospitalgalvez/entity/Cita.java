package com.umg.hospitalgalvez.hospitalgalvez.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "cita")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cita;
    private Long id_paciente;
    private Timestamp fecha_cita;
    private String descripcion;
    private Long id_usuario;
    private Long id_medico;
    private String estado;
}
