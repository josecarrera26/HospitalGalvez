package com.umg.hospitalgalvez.hospitalgalvez.repository;

import com.umg.hospitalgalvez.hospitalgalvez.dto.EditarReceta;
import com.umg.hospitalgalvez.hospitalgalvez.entity.DetalleReceta;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Receta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface DetalleRecetaRepository extends JpaRepository<DetalleReceta, Long> {
@Modifying
    @Query("DELETE FROM DetalleReceta d WHERE d.receta.id_receta = :idReceta")
    void deleteByRecetaId(Long idReceta);

    List<Object[]> findByReceta(Receta receta);

}
