package com.umg.hospitalgalvez.hospitalgalvez.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umg.hospitalgalvez.hospitalgalvez.entity.Examen;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Medicamento;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Receta;
import com.umg.hospitalgalvez.hospitalgalvez.repository.MedicamentoRepository;
import com.umg.hospitalgalvez.hospitalgalvez.repository.RecetaRepository;

@Service
public class MedicamentoService {
    private final MedicamentoRepository medicamentoRepository;

    @Autowired
    public MedicamentoService(MedicamentoRepository medicamentoRepository) {
        this.medicamentoRepository = medicamentoRepository;
    }

    public Medicamento create(Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    }

    public List<Medicamento> getAll() {
        return medicamentoRepository.findAll();
    }

    public Medicamento update(Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    }

    public Optional<Medicamento> findById(Long id) {
        return medicamentoRepository.findById(id);
    }

    public Optional<Medicamento> getById(Long id) {
        return medicamentoRepository.findById(id);
    }
}
