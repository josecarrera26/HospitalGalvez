package com.umg.hospitalgalvez.hospitalgalvez.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umg.hospitalgalvez.hospitalgalvez.entity.Historial;
import com.umg.hospitalgalvez.hospitalgalvez.services.FacturaService;
import com.umg.hospitalgalvez.hospitalgalvez.services.HistorialService;
import com.umg.hospitalgalvez.hospitalgalvez.services.PacienteService;

@RestController
@RequestMapping("/historial")
@PreAuthorize("hasAuthority('administrador') or hasAuthority('secretaria') or hasAuthority('doctor') ")
public class HistorialController {
    @Autowired
    private final HistorialService historialService;
    private final PacienteService pacienteService;
    private final FacturaService facturaService;

    public HistorialController(HistorialService historialService, PacienteService pacienteService, FacturaService facturaService){
        this.historialService = historialService;
        this.pacienteService = pacienteService;
        this.facturaService = facturaService;
    }

    @GetMapping
    public ResponseEntity<List<Historial>> getAll() {
        List<Historial> respuesta = historialService.getAll();
        if (respuesta != null) {
            return ResponseEntity.ok(respuesta);
        }
        return ResponseEntity.notFound().build();
    }

   
}
