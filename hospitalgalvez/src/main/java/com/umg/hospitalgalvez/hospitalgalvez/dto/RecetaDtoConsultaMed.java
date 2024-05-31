package com.umg.hospitalgalvez.hospitalgalvez.dto;



import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecetaDtoConsultaMed {
 private Long id_receta;
    private Long id_cita;
    private Date fecha;
    private String nombre;
    private String nombre_medico;
}
