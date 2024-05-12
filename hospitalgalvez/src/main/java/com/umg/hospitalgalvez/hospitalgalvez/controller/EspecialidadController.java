package com.umg.hospitalgalvez.hospitalgalvez.controller;

import org.springframework.web.bind.annotation.RestController;
import com.umg.hospitalgalvez.hospitalgalvez.dto.EspecialidadDto;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Especialidad;
import com.umg.hospitalgalvez.hospitalgalvez.services.EspecialidadService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/especialidades")
public class EspecialidadController {

    private final EspecialidadService especialidadService;

    @Autowired
    public EspecialidadController (EspecialidadService especialidadService){
        this.especialidadService = especialidadService;
    }


    @GetMapping("/consultar")
    public ResponseEntity<List<Especialidad>> getList() {
        return ResponseEntity.ok(especialidadService.getAll());
    }

    @PostMapping("/registrar")
    public ResponseEntity<Especialidad> create(@RequestBody EspecialidadDto especialidadJson){
        {
        Especialidad especialidad = new Especialidad();
        especialidad.setId_especialidad(especialidadJson.getId_especialidad());
        especialidad.setNombre_especialidad(especialidadJson.getNombre_especialidad());     
        especialidad.setDescripcion(especialidadJson.getDescripcion());
        especialidad.setEstado(especialidadJson.getEstado());

        Especialidad creatEspecialidad = especialidadService.create(especialidad);
        return new ResponseEntity<>(creatEspecialidad, HttpStatus.CREATED);
        }
    }
}