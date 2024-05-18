package com.umg.hospitalgalvez.hospitalgalvez.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.umg.hospitalgalvez.hospitalgalvez.dto.MedicamentoDto;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Medicamento;
import com.umg.hospitalgalvez.hospitalgalvez.services.MedicamentoService;

@RestController
@RequestMapping("medicamento")
public class MedicamentoController {
    private final MedicamentoService medicamentoService;

    @Autowired
    public MedicamentoController(MedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }

    @PostMapping
    public ResponseEntity<Medicamento> save(@RequestBody MedicamentoDto medicamentoJson) {
        Medicamento medicamento = new Medicamento();
        medicamento.setCodigo_medicamento(medicamentoJson.getCodigo_medicamento());
        medicamento.setNombre_medicamento(medicamentoJson.getNombre_medicamento());
        medicamento.setTipo_medida(medicamentoJson.getTipo_medida());
        medicamento.setTipo_medicamento(medicamentoJson.getTipo_medicamento());
        
        Medicamento medObj = medicamentoService.create(medicamento);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(medObj.getId_medicamento())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<Medicamento>> getList() {
        List<Medicamento> response = medicamentoService.getAll();
        if (response.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(response);
        }
    }

    @PatchMapping()
    public ResponseEntity<Object> update(@RequestBody MedicamentoDto medjson) {
        Medicamento med = new Medicamento();
        med.setId_medicamento(medjson.getId_medicamento());
        med.setNombre_medicamento(medjson.getNombre_medicamento());
        med.setTipo_medida(medjson.getTipo_medida());
        med.setTipo_medicamento(medjson.getTipo_medicamento());
        med.setEstado(medjson.getEstado());

        Medicamento medobj = medicamentoService.update(med);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(medobj.getId_medicamento())
                .toUri();
        return ResponseEntity.ok(location);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Medicamento>> getById(@PathVariable Long id){
        final Optional<Medicamento> med;
        med = medicamentoService.getById(id);
        if (med.isPresent()) {
            return ResponseEntity.ok(med);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

}
