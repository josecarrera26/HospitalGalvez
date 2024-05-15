package com.umg.hospitalgalvez.hospitalgalvez.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="detalle_receta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleReceta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_detalle_receta;

    @ManyToOne
    @JoinColumn(name = "id_receta")
    private Receta receta;
    
    @ManyToOne
    @JoinColumn(name = "id_medicamento")
    private Medicamento medicamento;

    public DetalleReceta(Receta receta, Medicamento medicamento){
        this.receta = receta;
        this.medicamento = medicamento;
    }

}
