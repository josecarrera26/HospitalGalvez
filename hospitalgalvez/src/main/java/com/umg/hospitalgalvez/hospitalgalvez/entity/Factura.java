
package com.umg.hospitalgalvez.hospitalgalvez.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Table(name = "factura")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_factura;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_factura", nullable = false, updatable = false)
    private Date fechaCreacion;
    
    @ManyToOne
    @JoinColumn(name = "id_cita")
    private Cita cita;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private String nit;

    private Double total;

    @PrePersist
    protected void onCreate() {
        fechaCreacion = new Date();
    }
}
