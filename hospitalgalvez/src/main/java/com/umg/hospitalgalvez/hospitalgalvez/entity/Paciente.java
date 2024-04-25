package com.umg.hospitalgalvez.hospitalgalvez.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "paciente")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
