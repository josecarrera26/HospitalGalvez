package com.umg.hospitalgalvez.hospitalgalvez.services;

import com.umg.hospitalgalvez.hospitalgalvez.dto.RecetaDto;
import com.umg.hospitalgalvez.hospitalgalvez.dto.RecetaDtoConsultaMed;
import com.umg.hospitalgalvez.hospitalgalvez.entity.DetalleReceta;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Receta;
import com.umg.hospitalgalvez.hospitalgalvez.repository.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class RecetaService {

    @Autowired
    private final RecetaRepository recetaRepository;
    private final DetalleRecetaService detalleRecetasService;

    public RecetaService(RecetaRepository recetaRepository, DetalleRecetaService detalleRecetaService) {
        this.recetaRepository = recetaRepository;
        this.detalleRecetasService = detalleRecetaService;
    }

    @Transactional
    public Receta create(Receta receta, List<DetalleReceta> detalles) {
        try {
            Receta recetaobj = recetaRepository.save(receta);

            for (DetalleReceta det : detalles) {
                det.setReceta(recetaobj);
                detalleRecetasService.create(det);
            }
            return recetaobj;
        } catch (Exception e) {
            throw e;
            // TODO: handle exception
        }
    }

    @Transactional
    public List<Receta> getAll() {
        return recetaRepository.findAll();
    }

    @Transactional
    public Receta update(Receta receta, List<DetalleReceta> detalles) {
        if (detalleRecetasService.delete(receta.getId_receta())) {
            try {
                Receta recetaobj = recetaRepository.save(receta);

                for (DetalleReceta det : detalles) {
                    det.setReceta(recetaobj);
                    detalleRecetasService.create(det);
                }
                return recetaobj;
            } catch (Exception e) {
                throw e;
                // TODO: handle exception
            }
        }
        return recetaRepository.save(receta);
    }

    @Transactional
    public Optional<Receta> findById(Long id) {
        return recetaRepository.findById(id);
    }

    public List<RecetaDtoConsultaMed> getrecetaByMedico(Long id_receta, Long id_medico) {
        List<Object[]> results = recetaRepository.findRecetasByMedico(id_receta, id_medico);

        List<RecetaDtoConsultaMed> recetas = new ArrayList<>();

        for (Object[] row : results) {
            RecetaDtoConsultaMed recetaDto = new RecetaDtoConsultaMed();
            recetaDto.setId_receta((Long) row[0]);
            recetaDto.setId_cita((Long) row[1]);
            recetaDto.setFecha((Date) row[2]);
            recetaDto.setNombre((String) row[3]);
            recetaDto.setNombre_medico((String) row[4]);
            recetas.add(recetaDto);
        }
        return recetas;
    }
}
