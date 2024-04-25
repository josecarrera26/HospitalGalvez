package com.umg.hospitalgalvez.hospitalgalvez.services;

import com.umg.hospitalgalvez.hospitalgalvez.entity.Receta;
import com.umg.hospitalgalvez.hospitalgalvez.repository.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecetaService {
    private final RecetaRepository recetaRepository;

    @Autowired
    public RecetaService(RecetaRepository recetaRepository) {
        this.recetaRepository = recetaRepository;
    }

    public List<Receta> getAll(){
        return recetaRepository.findAll();
    }
}
