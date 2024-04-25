package com.umg.hospitalgalvez.hospitalgalvez.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecetaDto {
    private Long id_receta;
    private Long id_cita;
    private Long id_detalle_receta;
}
