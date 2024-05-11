package com.umg.hospitalgalvez.hospitalgalvez.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "historial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Historial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_historial;

    private Long id_paciente;
    private Timestamp fecha;
    private Long id_detalle;
    private String diagnostico;
    private Long id_factura;
    private Long id_detalle_historial;

}
