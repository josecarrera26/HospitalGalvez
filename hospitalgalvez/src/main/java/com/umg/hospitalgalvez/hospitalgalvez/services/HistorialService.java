package com.umg.hospitalgalvez.hospitalgalvez.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umg.hospitalgalvez.hospitalgalvez.entity.Historial;
import com.umg.hospitalgalvez.hospitalgalvez.repository.HistorialRepository;

@Service
public class HistorialService {
    private final HistorialRepository historialRepository;

    @Autowired
    public HistorialService(HistorialRepository historialRepository){
        this.historialRepository = historialRepository;
    }

     
    public List<Historial> getAll() {
        return historialRepository.findAll();
    }

    public Historial create(Historial historial) {
        return historialRepository.save(historial);

    }


    //falta agregar consulta por id paciente
    

}
