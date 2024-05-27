package com.umg.hospitalgalvez.hospitalgalvez.services;

import com.umg.hospitalgalvez.hospitalgalvez.dto.DetalleRecetaDto;
import com.umg.hospitalgalvez.hospitalgalvez.entity.DetalleReceta;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Medicamento;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Receta;
import com.umg.hospitalgalvez.hospitalgalvez.repository.DetalleRecetaRepository;
import com.umg.hospitalgalvez.hospitalgalvez.repository.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class RecetaService {
    private final RecetaRepository recetaRepository;
    private final DetalleRecetaService detalleRecetasService;

    @Autowired
    public RecetaService(RecetaRepository recetaRepository, DetalleRecetaService detalleRecetaService) {
        this.recetaRepository = recetaRepository;
        this.detalleRecetasService = detalleRecetaService;
    }

    @Transactional
    public Receta create(Receta receta, List<DetalleRecetaDto> detalles) {
        try {
            Receta recetaobj = recetaRepository.save(receta);

            for (DetalleRecetaDto det : detalles) {
                DetalleReceta detreceta = new DetalleReceta();
                System.out.println("FOR DETALLE" + det.getId_medicamento());
                detreceta.getMedicamento().setId_medicamento(det.getId_medicamento());
                detreceta.setDescripcion(det.getDescripciones());
                detreceta.getReceta().setId_receta(recetaobj.getId_receta());
                detalleRecetasService.create(detreceta);
            }
            return recetaobj;
        } catch (Exception e) {
            throw e;
            // TODO: handle exception
        }
    }

    public List<Receta> getAll() {
        return recetaRepository.findAll();
    }

    public Receta update(Receta receta) {
        return recetaRepository.save(receta);
    }

    public Optional<Receta> findById(Long id) {
        return recetaRepository.findById(id);
    }
}
