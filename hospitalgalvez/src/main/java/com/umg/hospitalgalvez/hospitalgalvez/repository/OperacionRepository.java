package com.umg.hospitalgalvez.hospitalgalvez.repository;

import com.umg.hospitalgalvez.hospitalgalvez.entity.Operacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperacionRepository extends JpaRepository <Operacion, Long> {
}
