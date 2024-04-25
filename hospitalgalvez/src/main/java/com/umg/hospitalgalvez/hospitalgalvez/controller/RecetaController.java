package com.umg.hospitalgalvez.hospitalgalvez.controller;

import com.umg.hospitalgalvez.hospitalgalvez.entity.Receta;
import com.umg.hospitalgalvez.hospitalgalvez.services.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("recetas")
public class RecetaController {

    private final RecetaService recetaService;

    @Autowired
    public RecetaController(RecetaService recetaService) {
        this.recetaService = recetaService;
    }

    @GetMapping
    public ResponseEntity<List<Receta>> getAll(){
        List<Receta> respuesta = new ArrayList<>();
        respuesta = recetaService.getAll();
        if (respuesta != null) {
            return ResponseEntity.ok(respuesta);
        }
        return  ResponseEntity.notFound().build();
    }

}
