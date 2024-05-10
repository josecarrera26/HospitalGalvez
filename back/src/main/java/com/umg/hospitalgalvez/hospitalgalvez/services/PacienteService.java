package com.umg.hospitalgalvez.hospitalgalvez.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umg.hospitalgalvez.hospitalgalvez.entity.Paciente;
import com.umg.hospitalgalvez.hospitalgalvez.repository.PacienteRepository;

@Service
public class PacienteService {
    @Autowired
    private final PacienteRepository repository;

    public PacienteService(PacienteRepository repository){
        this.repository = repository;
    }

    public List<Paciente> getAll(){
        return repository.findAll();
    }
    public Optional<Paciente>getById(Long id){
        return repository.findById(id);
    }

    public Paciente create(Paciente paciente){
        return repository.save(paciente);
    }
}
