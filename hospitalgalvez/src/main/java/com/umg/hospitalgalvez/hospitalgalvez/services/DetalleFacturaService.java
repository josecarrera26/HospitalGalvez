package com.umg.hospitalgalvez.hospitalgalvez.services;


import com.umg.hospitalgalvez.hospitalgalvez.entity.DetalleFactura;
import com.umg.hospitalgalvez.hospitalgalvez.repository.DetalleFacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleFacturaService {

    
    private final DetalleFacturaRepository detalleFacturaRepository;

    @Autowired
    public DetalleFacturaService(DetalleFacturaRepository detalleFacturaRepository) {
        this.detalleFacturaRepository = detalleFacturaRepository;
    }

    @Transactional
   public DetalleFactura create(DetalleFactura detrefact) {
        return detalleFacturaRepository.save(detrefact);
    }

    public List<DetalleFactura> getAll() {
        return detalleFacturaRepository.findAll();
    }

    public DetalleFactura update(DetalleFactura detrefact) {
        return detalleFacturaRepository.save(detrefact);
    }

    public Optional<DetalleFactura> findById(Long idmed) {
        return detalleFacturaRepository.findById(idmed);
    }


}
