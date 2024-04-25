package com.umg.hospitalgalvez.hospitalgalvez.controller;

import com.umg.hospitalgalvez.hospitalgalvez.entity.Operacion;
import com.umg.hospitalgalvez.hospitalgalvez.services.OperacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("operaciones")
public class OperacionController {

    private final OperacionService operacionService;

    @Autowired
    public OperacionController(OperacionService operacionService) {
        this.operacionService = operacionService;
    }

    @GetMapping
    public ResponseEntity<List<Operacion>> getList(){
        return ResponseEntity.ok(operacionService.getAll());
    }
}
