package com.umg.hospitalgalvez.hospitalgalvez.controller;

import com.umg.hospitalgalvez.hospitalgalvez.dto.DetalleRecetaDto;
import com.umg.hospitalgalvez.hospitalgalvez.entity.DetalleReceta;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Medicamento;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Receta;
import com.umg.hospitalgalvez.hospitalgalvez.services.DetalleRecetaService;
import com.umg.hospitalgalvez.hospitalgalvez.services.MedicamentoService;
import com.umg.hospitalgalvez.hospitalgalvez.services.RecetaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("detallerecetas")
public class DetalleRecetaController {
    private final DetalleRecetaService detalleRecetaService;
    private final MedicamentoService medicamentoService;
    private final RecetaService recetaService;

    @Autowired
    public DetalleRecetaController(DetalleRecetaService detalleRecetaService, MedicamentoService medicamentoService,
            RecetaService recetaService) {
        this.detalleRecetaService = detalleRecetaService;
        this.medicamentoService = medicamentoService;
        this.recetaService = recetaService;
    }

    @GetMapping
    public ResponseEntity<List<DetalleReceta>> getAll() {
        List<DetalleReceta> respuesta = detalleRecetaService.getAll();
        if (respuesta != null) {
            return ResponseEntity.ok(respuesta);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<DetalleReceta> save(@RequestBody DetalleRecetaDto detrecetaJson) {

        final Optional<Medicamento> med;
        System.out.println("ES ES EN LA BUSQUEDA DE MED" + detrecetaJson.getId_medicamento());
        med = medicamentoService.findById(detrecetaJson.getId_medicamento());

        final Optional<Receta> receta;
        receta = recetaService.findById(detrecetaJson.getId_receta());
        // save
        DetalleReceta detreceta = new DetalleReceta();
        detreceta.setMedicamento(med.get());
        detreceta.setReceta(receta.get());

        detreceta.getMedicamento().setId_medicamento(detrecetaJson.getId_medicamento());
    
        detreceta.getReceta().setId_receta(detrecetaJson.getId_receta());

        DetalleReceta detrecetaObj = detalleRecetaService.create(detreceta);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(detrecetaObj.getId_detalle_receta())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}