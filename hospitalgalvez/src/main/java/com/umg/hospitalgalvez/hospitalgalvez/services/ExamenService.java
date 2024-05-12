package com.umg.hospitalgalvez.hospitalgalvez.services;

import com.umg.hospitalgalvez.hospitalgalvez.entity.Examen;
import com.umg.hospitalgalvez.hospitalgalvez.repository.ExamenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamenService {

    private final ExamenRepository examenRepository;

    @Autowired
    public ExamenService(ExamenRepository examenRepository) {
        this.examenRepository = examenRepository;
    }

    public List<Examen> getAll() {
        return examenRepository.findAll();
    }

    public Optional<Examen> getById(Long id) {
        return examenRepository.findById(id);
    }

    public Examen save(Examen examen) {
        return examenRepository.save(examen);
    }

    public boolean delete(Examen examen) {
        try {
            examenRepository.delete(examen);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public Examen update(Examen examen) {
        return examenRepository.save(examen);
    }
}
