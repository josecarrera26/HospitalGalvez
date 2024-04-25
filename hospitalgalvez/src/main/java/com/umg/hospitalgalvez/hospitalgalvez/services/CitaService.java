package com.umg.hospitalgalvez.hospitalgalvez.services;

import com.umg.hospitalgalvez.hospitalgalvez.entity.Cita;
import com.umg.hospitalgalvez.hospitalgalvez.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitaService {
    private final CitaRepository citaRepository;

    @Autowired
    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    public List<Cita> getAll(){
        return citaRepository.findAll();
    }
}
