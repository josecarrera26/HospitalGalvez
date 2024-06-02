package com.umg.hospitalgalvez.hospitalgalvez.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditarReceta {
private Long id_cita;
    private Date fecha;
    private String nombre;
    private String nombre_medico;
    private List<DetalleMedicamento> detalle;
}
