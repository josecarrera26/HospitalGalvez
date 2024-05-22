package com.umg.hospitalgalvez.hospitalgalvez.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicamentoDto {
    private Long id_medicamento;

    private String codigo_medicamento;

    private String nombre_medicamento;

    private String tipo_medida;

    

    private String estado;
    private String tipo_medicamento;

}
