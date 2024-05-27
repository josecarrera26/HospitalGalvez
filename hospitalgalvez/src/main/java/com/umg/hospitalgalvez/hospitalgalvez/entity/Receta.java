package com.umg.hospitalgalvez.hospitalgalvez.entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "receta")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Receta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_receta;

    @ManyToOne
    @JoinColumn(name = "id_cita")
    private Cita cita;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha", nullable = false, updatable = false)
 
    private Date fechaCreacion;

    @PrePersist
    protected void onCreate() {
        fechaCreacion = new Date();
    }
}
