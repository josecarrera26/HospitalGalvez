package com.umg.hospitalgalvez.hospitalgalvez.services;

import com.umg.hospitalgalvez.hospitalgalvez.entity.Receta;
import com.umg.hospitalgalvez.hospitalgalvez.repository.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecetaService {
    private final RecetaRepository recetaRepository;

    @Autowired
    public RecetaService(RecetaRepository recetaRepository) {
        this.recetaRepository = recetaRepository;
    }

    public Receta create(Receta receta) {
        return recetaRepository.save(receta);
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
