package com.umg.hospitalgalvez.hospitalgalvez.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umg.hospitalgalvez.hospitalgalvez.entity.Medicamento;


public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    Optional<Medicamento> findByCodigoAndEstado(String codigo, String estado);
}
