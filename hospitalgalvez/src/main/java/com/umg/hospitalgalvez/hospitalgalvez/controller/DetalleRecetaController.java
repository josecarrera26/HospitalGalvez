package com.umg.hospitalgalvez.hospitalgalvez.controller;

import com.umg.hospitalgalvez.hospitalgalvez.entity.DetalleReceta;
import com.umg.hospitalgalvez.hospitalgalvez.services.DetalleRecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("detallerecetas")
public class DetalleRecetaController {
    private final DetalleRecetaService detalleRecetaService;

    @Autowired
    public DetalleRecetaController(DetalleRecetaService detalleRecetaService) {
        this.detalleRecetaService = detalleRecetaService;
    }

    @GetMapping
    public ResponseEntity<List<DetalleReceta>> getAll(){
        List<DetalleReceta> respuesta = new ArrayList<>();
        respuesta = detalleRecetaService.getAll();
        if (respuesta != null) {
            return ResponseEntity.ok(respuesta);
        }
        return  ResponseEntity.notFound().build();
    }
}
