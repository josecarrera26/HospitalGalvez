package com.umg.hospitalgalvez.hospitalgalvez.controller;

import com.umg.hospitalgalvez.hospitalgalvez.entity.Cita;
import com.umg.hospitalgalvez.hospitalgalvez.services.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("citas")
public class CitaController {

    private final CitaService citaService;

    @Autowired
    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @GetMapping
    public ResponseEntity<List<Cita>> getAll() {
        List<Cita> respuesta = new ArrayList<>();
        respuesta = citaService.getAll();
        if (respuesta != null) {
            return ResponseEntity.ok(respuesta);
        }
        return ResponseEntity.notFound().build();
    }

}
