package com.umg.hospitalgalvez.hospitalgalvez.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umg.hospitalgalvez.hospitalgalvez.entity.ParametroConsultaLegado;

public interface ParametroConsultaLegadoRepository extends JpaRepository<ParametroConsultaLegado, Long> {
    ParametroConsultaLegado findByIdParametroConsultaLegado(Long idParametroConsultaLegado);
}