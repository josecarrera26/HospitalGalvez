package com.umg.hospitalgalvez.hospitalgalvez.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umg.hospitalgalvez.hospitalgalvez.entity.Factura;
import com.umg.hospitalgalvez.hospitalgalvez.services.FacturaService;

@RestController
@RequestMapping("facturas")
public class FacturaController {

    @Autowired
    private final FacturaService facturaService;


    

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }




    @GetMapping
    public ResponseEntity<List<Factura>> getAll() {
        List<Factura> respuesta = new ArrayList<>();
        respuesta = facturaService.getAll();
        if (respuesta != null) {
            return ResponseEntity.ok(respuesta);
        }
        return ResponseEntity.notFound().build();
    }
}
