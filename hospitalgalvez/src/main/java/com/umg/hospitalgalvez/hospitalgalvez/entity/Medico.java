package com.umg.hospitalgalvez.hospitalgalvez.entity;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "medico")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id_medico;
    private String nombre_medico;
    private String apellido_medico;
    private Long id_especialidad;
    private Long id_usuario;
    private String telefono;
    private String jornada;
    private String cod_jefe_inmediato;

    // Campos no persistentes
    @Transient
    private String colegiado;
    @Transient
    private Date fechaCreacion;
    @Transient
    private String direccion;
    @Transient
    private String centro_hospitalario;
    @Transient
    private int edad;
    @Transient
    private String Observaciones;
}