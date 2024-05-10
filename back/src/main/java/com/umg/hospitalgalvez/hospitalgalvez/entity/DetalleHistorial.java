package com.umg.hospitalgalvez.hospitalgalvez.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "detalle_historial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleHistorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_detalle_historial;

    private String item;
    private Long id_item;
    private String descripcion;
    private Long id_historial;

}
