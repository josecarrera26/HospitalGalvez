package com.umg.hospitalgalvez.hospitalgalvez.controller;

import com.umg.hospitalgalvez.hospitalgalvez.entity.Operacion;
import com.umg.hospitalgalvez.hospitalgalvez.services.OperacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
        List<Operacion> respuesta;
        respuesta = operacionService.getAll();
        if (respuesta != null) {
            return ResponseEntity.ok(respuesta);
        }
        return  ResponseEntity.notFound().build();
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        final Optional<Operacion> operacion;
        operacion = operacionService.findById(id);

        boolean respuesta = operacionService.delete(id);
        if (respuesta){
            return ResponseEntity.ok(operacion);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Operacion>> getById(@PathVariable Long id) {
        final Optional<Operacion> operacion;
        operacion = operacionService.findById(id);
        if (operacion.isPresent()) {
            return ResponseEntity.ok(operacion);
        }
        return  ResponseEntity.notFound().build();
    }

    @PutMapping()
    public ResponseEntity<Object> update(@RequestBody Operacion operacionJson) {
        Operacion operacion = new Operacion();

        operacion.setId_operacion(operacionJson.getId_operacion());
        operacion.setDescripcion(operacionJson.getDescripcion());

        Operacion e = operacionService.update(operacion);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(e.getId_operacion())
                .toUri();

        return ResponseEntity.ok(location);
    }

    @PostMapping()
    public ResponseEntity<Object> create(@RequestBody Operacion operacionJson) {
        Operacion operacion = new Operacion();
        operacion.setId_operacion(operacionJson.getId_operacion());
        operacion.setDescripcion(operacionJson.getDescripcion());

        Operacion e = operacionService.save(operacion);
        if (e != null) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(e.getId_operacion())
                    .toUri();

            return ResponseEntity.created(location).build();
        }

        return  ResponseEntity.internalServerError().build();

    }
}
