package com.umg.hospitalgalvez.hospitalgalvez.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleMedicamento {
    private Long id_medicamento;
    private String nombre_medicamento;
    private String descripcion;
}
