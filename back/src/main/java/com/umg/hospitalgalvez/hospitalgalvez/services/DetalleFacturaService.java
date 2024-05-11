package com.umg.hospitalgalvez.hospitalgalvez.services;


import com.umg.hospitalgalvez.hospitalgalvez.entity.DetalleFactura;
import com.umg.hospitalgalvez.hospitalgalvez.repository.DetalleFacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleFacturaService {

    private final DetalleFacturaRepository detalleFacturaRepository;

    @Autowired
    public DetalleFacturaService(DetalleFacturaRepository detalleFacturaRepository) {
        this.detalleFacturaRepository = detalleFacturaRepository;
    }

    public List<DetalleFactura> getAll(){
        return detalleFacturaRepository.findAll();
    }


}
