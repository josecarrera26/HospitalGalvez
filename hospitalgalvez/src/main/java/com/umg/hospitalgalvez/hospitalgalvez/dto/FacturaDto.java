package com.umg.hospitalgalvez.hospitalgalvez.dto;


import com.umg.hospitalgalvez.hospitalgalvez.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturaDto {
    private Long id_factura;
    private Long id_cita;
    private Date fecha_factura;
    private Usuario id_usuario;
    private Long [] id_medicamento;
    private String nit;
}
