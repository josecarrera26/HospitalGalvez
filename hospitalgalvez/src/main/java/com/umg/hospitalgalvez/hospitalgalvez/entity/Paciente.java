package com.umg.hospitalgalvez.hospitalgalvez.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "paciente")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {
    private Long id;
    private String nombre;
    private String apellido;
    private String fecha_nacimiento;
    private Double direcicon;
    private String telefono;
    private int dpi;
    private String nit;
    private String email;
    private String genero;
    private int id_usuario;
    private String estado;
}
