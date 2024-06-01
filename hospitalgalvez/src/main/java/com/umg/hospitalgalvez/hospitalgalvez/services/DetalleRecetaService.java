package com.umg.hospitalgalvez.hospitalgalvez.services;

import com.umg.hospitalgalvez.hospitalgalvez.dto.DetalleMedicamento;
import com.umg.hospitalgalvez.hospitalgalvez.dto.EditarReceta;
import com.umg.hospitalgalvez.hospitalgalvez.entity.DetalleReceta;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Receta;
import com.umg.hospitalgalvez.hospitalgalvez.repository.DetalleRecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DetalleRecetaService {
    @Autowired

    private final DetalleRecetaRepository detalleRecetaRepository;

    public DetalleRecetaService(DetalleRecetaRepository detalleRecetaRepository) {
        this.detalleRecetaRepository = detalleRecetaRepository;
    }

    @Transactional
    public DetalleReceta create(DetalleReceta detreceta) {

        return detalleRecetaRepository.save(detreceta);
    }

    public List<DetalleReceta> getAll() {
        return detalleRecetaRepository.findAll();
    }

    public DetalleReceta update(DetalleReceta detreceta) {
        return detalleRecetaRepository.save(detreceta);
    }

    public boolean delete(Long idReceta) {
        try {
            System.out.println("ENTRE A ELIMINAR DETALLE");
            detalleRecetaRepository.deleteByRecetaId(idReceta);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Optional<DetalleReceta> findById(Long idmed) {
        return detalleRecetaRepository.findById(idmed);
    }

    public List<EditarReceta> geteditarReceta(Long id_receta) {
        List<Object[]> results = detalleRecetaRepository.findByReceta(id_receta);
        List<EditarReceta> recetas = new ArrayList<>();
        if (results.isEmpty()) {
            return recetas;
        }
        EditarReceta recetaDto = new EditarReceta();
        List<DetalleMedicamento> detalleMedicamentos = new ArrayList<>();
        for (Object[] row : results) {

            recetaDto.setId_cita((Long) row[0]);
            recetaDto.setFecha((Date) row[1]);
            recetaDto.setNombre((String) row[2]);
            recetaDto.setNombre_medico((String) row[3]);
            DetalleMedicamento detalleMedicamento = new DetalleMedicamento();
            detalleMedicamento.setNombre_medicamento((String) row[4]);
            detalleMedicamento.setId_medicamento((Long) row[5]);
            detalleMedicamento.setDescripcion((String) row[6]);

            detalleMedicamentos.add(detalleMedicamento);
        }
        recetaDto.setDetalle(detalleMedicamentos);
        recetas.add(recetaDto);
        return recetas;
    }

}
