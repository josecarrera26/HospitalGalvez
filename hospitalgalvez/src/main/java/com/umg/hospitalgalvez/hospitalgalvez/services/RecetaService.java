package com.umg.hospitalgalvez.hospitalgalvez.services;

import com.umg.hospitalgalvez.hospitalgalvez.entity.DetalleReceta;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Medicamento;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Receta;
import com.umg.hospitalgalvez.hospitalgalvez.repository.DetalleRecetaRepository;
import com.umg.hospitalgalvez.hospitalgalvez.repository.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecetaService {
    private final RecetaRepository recetaRepository;
    private final DetalleRecetaRepository detalleRecetaRepository;

    @Autowired
    public RecetaService(RecetaRepository recetaRepository, DetalleRecetaRepository detalleRecetaRepository) {
        this.recetaRepository = recetaRepository;
        this.detalleRecetaRepository = detalleRecetaRepository;
    }

    @Transactional
    public Receta create(Receta receta) {
        Receta recetaobj = recetaRepository.save(receta); 
      //  int index = 0;
     //   for (Medicamento medicamento : medicamentos) {
      //     DetalleReceta detreceta = new DetalleReceta(recetaobj,medicamento);
      //      System.out.println("ESTO HAY EN EL FOR"+    medicamento);
            //detreceta.getMedicamento().setId_medicamento(medicamento.getId_medicamento());
            //detreceta.getReceta().setId_receta(recetaObj.getId_receta());

      //    DetalleReceta detrecetaObj = detalleRecetaRepository.save(detreceta);
     //   System.out.println("ESTO HAY EN DETALLE"+ detrecetaObj);
    //    }
        return recetaobj; 
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
