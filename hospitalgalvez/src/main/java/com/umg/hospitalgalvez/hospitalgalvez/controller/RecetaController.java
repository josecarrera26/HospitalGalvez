package com.umg.hospitalgalvez.hospitalgalvez.controller;

import com.umg.hospitalgalvez.hospitalgalvez.dto.RecetaDto;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Cita;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Receta;
import com.umg.hospitalgalvez.hospitalgalvez.services.CitaService;
import com.umg.hospitalgalvez.hospitalgalvez.services.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("recetas")
public class RecetaController {

    private final RecetaService recetaService;
    private final CitaService citaService;

    @Autowired
    public RecetaController(RecetaService recetaService, CitaService citaService) {
        this.recetaService = recetaService;
        this.citaService = citaService;
    }

      @PostMapping
    public ResponseEntity<Receta> save(@RequestBody RecetaDto recetaJson) {
        
        final Optional<Cita> cita;
        cita = citaService.findById(recetaJson.getId_cita());
        //save
        Receta receta = new Receta();
        receta.setCita(cita.get());
        receta.setFechaCreacion(recetaJson.getFecha());

        receta.getCita().setId_cita(recetaJson.getId_cita());

        Receta recetaObj = recetaService.create(receta);

        URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(recetaObj.getId_receta())
        .toUri();
        return ResponseEntity.created(location).build();
    }



    @GetMapping
    public ResponseEntity<List<Receta>> getList(){
        List<Receta> response = recetaService.getAll();
        if(response.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(response);
        }
    }

     @PatchMapping()
    public ResponseEntity<Object> update(@RequestBody RecetaDto recetajson){
        Receta receta = new Receta();
        final Optional<Cita> cita;
        cita = citaService.findById(recetajson.getId_cita());
        receta.setCita(cita.get());
        receta.setFechaCreacion(recetajson.getFecha());
        
        Receta recetaobj =recetaService.update(receta);
        URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(recetaobj.getId_receta())
        .toUri();
        return ResponseEntity.ok(location);
    }



}
