package com.umg.hospitalgalvez.hospitalgalvez.controller;

import com.umg.hospitalgalvez.hospitalgalvez.entity.Examen;
import com.umg.hospitalgalvez.hospitalgalvez.services.ExamenService;
import com.umg.hospitalgalvez.hospitalgalvez.services.OperacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("examenes")
public class ExamenController {
    private final ExamenService examenService;
    private final OperacionService operacionService;

    @Autowired
    public ExamenController(ExamenService examenService, OperacionService operacionService) {
        this.examenService = examenService;
        this.operacionService = operacionService;
    }

    @GetMapping
    public ResponseEntity<List<Examen>> getList(){
        List<Examen> respuesta = new ArrayList<>();
        respuesta = examenService.getAll();
        if (respuesta != null) {
            return ResponseEntity.ok(respuesta);
        }
        return  ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Examen>> getExamenById(@PathVariable Long id){
        final Optional<Examen> examen;
        examen = examenService.getById(id);
        if (examen.isPresent()) {
            return ResponseEntity.ok(examen);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> addExamen(@RequestBody Examen examen){
        examen = examenService.save(examen);
        if (examen != null) {
            return ResponseEntity.ok(examen);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteExamen(@PathVariable Long id){
        final Optional<Examen> examen;
        examen = examenService.getById(id);

        if (examen.isPresent()) {
            examenService.delete(examen.get());
            return ResponseEntity.ok(examen);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping
    public ResponseEntity<Object> updateExamen(@RequestBody Examen examenJson){
        Examen examen = new Examen();

        examen.setId_examen(examenJson.getId_examen());
        examen.setDescripcion(examenJson.getDescripcion());
        examen.setPrecio(examenJson.getPrecio());
        examen.setObservaciones(examenJson.getObservaciones());

        Examen e = examenService.update(examen);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(e.getId_examen())
                .toUri();

        return ResponseEntity.ok(location);
    }


}
