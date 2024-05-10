package com.umg.hospitalgalvez.hospitalgalvez.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umg.hospitalgalvez.hospitalgalvez.entity.DetalleHistorial;
import com.umg.hospitalgalvez.hospitalgalvez.repository.DetalleHistorialRepository;

@Service
public class DetalleHistorialService {
    private final DetalleHistorialRepository detalleHistorialRepository;

    @Autowired
    public DetalleHistorialService(DetalleHistorialRepository detalleHistorialRepository){
        this.detalleHistorialRepository = detalleHistorialRepository;
    }

    public List<DetalleHistorial> getAll(){
        return detalleHistorialRepository.findAll();
    }

}
