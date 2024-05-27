package com.umg.hospitalgalvez.hospitalgalvez.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.umg.hospitalgalvez.hospitalgalvez.dto.FacturaDto;
import com.umg.hospitalgalvez.hospitalgalvez.dto.RecetaDto;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Cita;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Factura;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Medicamento;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Receta;
import com.umg.hospitalgalvez.hospitalgalvez.services.CitaService;
import com.umg.hospitalgalvez.hospitalgalvez.services.DetalleFacturaService;
import com.umg.hospitalgalvez.hospitalgalvez.services.FacturaService;
import com.umg.hospitalgalvez.hospitalgalvez.services.MedicamentoService;

@RestController
@RequestMapping("facturas")
public class FacturaController {

    @Autowired
    private final FacturaService facturaService;
    private final CitaService citaService;
    private final MedicamentoService medicamentoService;

    
    public FacturaController(FacturaService facturaService, CitaService citaService,
            DetalleFacturaService detalleFacturaService, MedicamentoService medicamentoService) {
        this.facturaService = facturaService;
        this.citaService = citaService;
        this.medicamentoService = medicamentoService;
    }

    @PostMapping
    public ResponseEntity<Factura> save(@RequestBody FacturaDto facturaJson) {

        final Optional<Cita> cita;
        cita = citaService.findById(facturaJson.getId_cita());


        // save
        Factura factura = new Factura();
        if (cita.isPresent()) {
            factura.setCita(cita.get());
            factura.getCita().setId_cita(facturaJson.getId_cita());
            factura.getCita().setUsuario(facturaJson.getId_usuario());

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // array de lons de id medicamento
        Long[] id_medicamentodto = facturaJson.getId_medicamento();

        List<Medicamento> medicamentos = new ArrayList<>();

        // for para agregar los medicamentos al array
        for (Long ids : id_medicamentodto) {
            Optional<Medicamento> med = medicamentoService.findById(ids);
            if (med.isPresent()) {
                Medicamento medicamento = med.get();
                medicamentos.add(medicamento);
            }
        }
        Factura facturaObj = new Factura();
        if (!medicamentos.isEmpty()) {
            facturaObj = facturaService.create(factura, medicamentos);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(facturaObj.getId_factura())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<Factura>> getList() {
        List<Factura> response = facturaService.getAll();
        if (response.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(response);
        }
    }

    @PatchMapping()
    public ResponseEntity<Object> update(@RequestBody FacturaDto facturajson) {
        Factura factura = new Factura();
        final Optional<Cita> cita;
        cita = citaService.findById(facturajson.getId_cita());
        factura.setId_factura(facturajson.getId_factura());
        factura.setCita(cita.get());
        factura.setFechaCreacion(facturajson.getFecha_factura());

        Factura facturaobj = facturaService.update(factura);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(facturaobj.getId_factura())
                .toUri();
        return ResponseEntity.ok(location);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Factura>> getById(@PathVariable Long id) {
        final Optional<Factura> med;
        med = facturaService.findById(id);
        if (med.isPresent()) {
            return ResponseEntity.ok(med);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
