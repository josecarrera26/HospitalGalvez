package com.umg.hospitalgalvez.hospitalgalvez.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umg.hospitalgalvez.hospitalgalvez.entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente,Long> {
    
}
