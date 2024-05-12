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

    public Especialidad create(Especialidad especialidad){
        return especialidadRepository.save(especialidad);
    }

    public Especialidad update(Especialidad especialidad){
        return especialidadRepository.save(especialidad);
    }

    public void delete(Long id){
        especialidadRepository.deleteById(id);
    }

    public Especialidad getById(Long id){
        return especialidadRepository.findById(id).orElse(null);
    }

}