package com.umg.hospitalgalvez.hospitalgalvez.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.umg.hospitalgalvez.hospitalgalvez.entity.DetalleFactura;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Factura;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Medicamento;
import com.umg.hospitalgalvez.hospitalgalvez.repository.DetalleFacturaRepository;
import com.umg.hospitalgalvez.hospitalgalvez.repository.FacturaRepository;

@Service
@Transactional(rollbackFor = Exception.class)
public class FacturaService {

    @Autowired
    private final FacturaRepository facturaRepository;
    private final DetalleFacturaRepository detalleFacturaRepository;

    
    public FacturaService(FacturaRepository facturaRepository, DetalleFacturaRepository detalleFacturaRepository) {
        this.facturaRepository = facturaRepository;
        this.detalleFacturaRepository = detalleFacturaRepository;
    }

    @Transactional
    public Factura create(Factura factura, List<Medicamento> medicamentos) {
        try {
            Factura facturaojt = facturaRepository.save(factura);
            for (Medicamento medicamento : medicamentos) {
                DetalleFactura detrefact = new DetalleFactura(facturaojt, medicamento);
                detrefact.getMedicamento().setId_medicamento(medicamento.getId_medicamento());
                detrefact.getFactura().setId_factura(detrefact.getId_detalle());
                detalleFacturaRepository.save(detrefact);
            }
            return facturaojt;
        } catch (Exception e) {
            throw e;
            // TODO: handle exception
        }
    }

    public List<Factura> getAll() {
        return facturaRepository.findAll();
    }

    public Factura update(Factura factura) {
        return facturaRepository.save(factura);
    }

    public Optional<Factura> findById(Long id) {
        return facturaRepository.findById(id);
    }

}
