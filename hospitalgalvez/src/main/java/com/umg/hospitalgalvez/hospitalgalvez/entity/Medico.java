package com.umg.hospitalgalvez.hospitalgalvez.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
    @ManyToOne
    @JoinColumn(name = "id_especialidad")
    private Especialidad id_especialidad;
    
    private Long id_usuario;
    private String telefono;
    private String jornada;
    private Integer cod_jefe_inmediato;

    @Column(name = "colegiado", length = 30)
    private String colegiado;

    @Column(name = "fechacreacion")
    private Date fechaCreacion;

    @Column(name = "direccion", length = 100)
    private String direccion;

    @Column(name = "centro_hospitalario", length = 50)
    private String centroHospitalario;

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "observaciones", length = 255)
    private String observaciones;
}