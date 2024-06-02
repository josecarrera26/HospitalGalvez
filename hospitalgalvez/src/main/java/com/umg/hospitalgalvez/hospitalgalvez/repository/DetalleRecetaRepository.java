package com.umg.hospitalgalvez.hospitalgalvez.repository;

import com.umg.hospitalgalvez.hospitalgalvez.dto.EditarReceta;
import com.umg.hospitalgalvez.hospitalgalvez.entity.DetalleReceta;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Receta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface DetalleRecetaRepository extends JpaRepository<DetalleReceta, Long> {
@Modifying
    @Query("DELETE FROM DetalleReceta d WHERE d.receta.id_receta = :idReceta")
    void deleteByRecetaId(Long idReceta);

    List<Object[]> findByReceta(Receta receta);

     @Query("select c.id_cita , c.fecha_cita , p.nombre , me.nombre_medico, m.nombre_medicamento, dr.medicamento.id_medicamento, dr.descripcion, c.descripcion, c.fecha_cita" + 
                  " from Receta r  join DetalleReceta dr on dr.receta.id_receta = r.id_receta join Medicamento m on m.id_medicamento = dr.medicamento.id_medicamento " + 
                  " join Cita c on c.id_cita = r.cita.id_cita join Medico me on me.id_medico  = c.medico.id_medico join Paciente p on p.id_paciente = c.paciente.id_paciente where r.id_receta = :id_receta")
    List<Object[]> findByReceta(@Param("id_receta") Long id_receta);

}
