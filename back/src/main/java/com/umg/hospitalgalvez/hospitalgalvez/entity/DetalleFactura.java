package com.umg.hospitalgalvez.hospitalgalvez.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="detalle_factura")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleFactura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_detalle;

    @ManyToOne
    @JoinColumn(name = "id_factura")
    private Factura factura;

    private Long id_medicamento;

    @ManyToOne
    @JoinColumn(name = "id_medicamento")
    private Medicamento medicamento;

    private Integer cantidad;
    private Double tarifa;
    private Double impuesto;
    private Double descuento;
    private Double subtotal;
    private Double iva;
    private Double total;
}
