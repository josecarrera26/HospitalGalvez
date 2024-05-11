package com.umg.hospitalgalvez.hospitalgalvez.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Especialidad;
import com.umg.hospitalgalvez.hospitalgalvez.repository.EspecialidadRepository;

@Service
public class EspecialidadService {
    private final EspecialidadRepository especialidadRepository;

    @Autowired
    public EspecialidadService(EspecialidadRepository especialidadRepository){
        this.especialidadRepository = especialidadRepository;
    }

    public List<Especialidad> getAll(){
        return especialidadRepository.findAll();
    }
}