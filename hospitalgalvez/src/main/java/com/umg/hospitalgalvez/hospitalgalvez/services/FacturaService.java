package com.umg.hospitalgalvez.hospitalgalvez.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umg.hospitalgalvez.hospitalgalvez.entity.Factura;
import com.umg.hospitalgalvez.hospitalgalvez.repository.FacturaRepository;

@Service
public class FacturaService {

    @Autowired
    private final FacturaRepository facturaRepository;

    public FacturaService(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }
    
    public List<Factura> getAll(){
        return facturaRepository.findAll();
    }



}
