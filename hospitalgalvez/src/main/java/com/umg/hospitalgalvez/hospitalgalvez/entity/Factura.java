
package com.umg.hospitalgalvez.hospitalgalvez.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "factura")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_factura;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;
    private Timestamp fecha_factura;
    private String descripcion_factura;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    

}
