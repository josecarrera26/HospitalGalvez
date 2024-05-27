package com.umg.hospitalgalvez.hospitalgalvez.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleRecetaDto {
    private Long id_receta;
    private Long id_medicamento;
    private String descripciones;
}
