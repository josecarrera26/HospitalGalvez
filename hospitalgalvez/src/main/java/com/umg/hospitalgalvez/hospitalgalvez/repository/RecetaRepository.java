package com.umg.hospitalgalvez.hospitalgalvez.repository;

import com.umg.hospitalgalvez.hospitalgalvez.entity.Receta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecetaRepository extends JpaRepository<Receta, Long> {
    @Query("select r.id_receta, c.id_cita, r.fechaCreacion, p.nombre , m.nombre_medico " +
            " from Receta r join Cita c on c.id_cita = r.cita.id_cita join Medico m on m.id_medico  = c.medico.id_medico"
            +
            " join Paciente p on p.id_paciente = c.paciente.id_paciente where r.id_receta = :id_receta and m.id_medico = :id_medico")
    List<Object[]> findRecetasByMedico(@Param("id_receta") Long id_receta, @Param("id_medico") Long id_medico);

}
