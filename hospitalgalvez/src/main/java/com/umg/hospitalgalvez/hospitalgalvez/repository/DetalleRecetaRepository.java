package com.umg.hospitalgalvez.hospitalgalvez.repository;

import com.umg.hospitalgalvez.hospitalgalvez.entity.DetalleReceta;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface DetalleRecetaRepository extends JpaRepository<DetalleReceta, Long> {
@Modifying
    @Query("DELETE FROM DetalleReceta d WHERE d.receta.id_receta = :idReceta")
    void deleteByRecetaId(Long idReceta);
}
