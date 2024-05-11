package com.umg.hospitalgalvez.hospitalgalvez.services;

import com.umg.hospitalgalvez.hospitalgalvez.entity.DetalleReceta;
import com.umg.hospitalgalvez.hospitalgalvez.repository.DetalleRecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleRecetaService {

    private final DetalleRecetaRepository detalleRecetaRepository;

    @Autowired
    public DetalleRecetaService(DetalleRecetaRepository detalleRecetaRepository) {
        this.detalleRecetaRepository = detalleRecetaRepository;
    }

    public List<DetalleReceta> getAll(){
        return detalleRecetaRepository.findAll();
    }
}
