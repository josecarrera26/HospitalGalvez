package com.umg.hospitalgalvez.hospitalgalvez.controller;


import com.umg.hospitalgalvez.hospitalgalvez.dto.DetalleFacturaDto;
import com.umg.hospitalgalvez.hospitalgalvez.entity.DetalleFactura;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Factura;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Medicamento;
import com.umg.hospitalgalvez.hospitalgalvez.services.DetalleFacturaService;
import com.umg.hospitalgalvez.hospitalgalvez.services.FacturaService;
import com.umg.hospitalgalvez.hospitalgalvez.services.MedicamentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("detallefacturas")
public class DetalleFacturaController {

    @Autowired
    private final DetalleFacturaService detalleFacturaService;
    private final MedicamentoService medicamentoService;
    private final FacturaService facturaService;

    
    public DetalleFacturaController(DetalleFacturaService detalleFacturaService, MedicamentoService medicamentoService,
            FacturaService facturaService) {
        this.detalleFacturaService = detalleFacturaService;
        this.medicamentoService = medicamentoService;
        this.facturaService = facturaService;
    }

    @GetMapping
    public ResponseEntity<List<DetalleFactura>> getAll() {
        List<DetalleFactura> respuesta = detalleFacturaService.getAll();
        if (respuesta != null) {
            return ResponseEntity.ok(respuesta);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<DetalleFactura> save(@RequestBody DetalleFacturaDto detfactJson) {

        final Optional<Medicamento> med;
        med = medicamentoService.findById(detfactJson.getId_medicamento());

        final Optional<Factura> factura;
        factura = facturaService.findById(detfactJson.getId_factura());
        // save
        DetalleFactura detfactura = new DetalleFactura();
        detfactura.setMedicamento(med.get());
        detfactura.setFactura(factura.get());

        detfactura.getMedicamento().setId_medicamento(detfactJson.getId_medicamento());
        detfactura.getFactura().setId_factura(detfactJson.getId_factura());
        detfactJson.getCantidad();

        DetalleFactura detfactObj = detalleFacturaService.create(detfactura);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(detfactObj.getId_detalle())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
