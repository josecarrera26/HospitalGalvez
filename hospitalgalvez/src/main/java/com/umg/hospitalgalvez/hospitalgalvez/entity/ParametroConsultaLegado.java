package com.umg.hospitalgalvez.hospitalgalvez.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Parametro_Consulta_Legado")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParametroConsultaLegado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parametro_consulta_legado")
    private Long idParametroConsultaLegado;

    @Column(name = "nombre_parametro_consulta_legado")
    private String nombreParametroConsultaLegado;

    @Column(name = "descripcion_parametro_consulta_legado")
    private String descripcionParametroConsultaLegado;

    @Column(name = "validador_parametro_consulta_legado")
    private String validadorParametroConsultaLegado;

    public Long getId_parametro_consulta_legado() {
        return idParametroConsultaLegado;
    }

    public void setId_parametro_consulta_legado(Long idParametroConsultaLegado) {
        this.idParametroConsultaLegado = idParametroConsultaLegado;
    }

    public String getValidador_parametro_consulta_legado() {
        return validadorParametroConsultaLegado;
    }

    public void setValidador_parametro_consulta_legado(String validadorParametroConsultaLegado) {
        this.validadorParametroConsultaLegado = validadorParametroConsultaLegado;
    }
}