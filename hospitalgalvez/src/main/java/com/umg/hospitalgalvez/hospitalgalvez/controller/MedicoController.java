package com.umg.hospitalgalvez.hospitalgalvez.controller;

import java.util.List;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.umg.hospitalgalvez.hospitalgalvez.dto.MedicoDto;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Medico;
import com.umg.hospitalgalvez.hospitalgalvez.services.MedicoService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping ("api/medico")

public class MedicoController{
    
    @Autowired
    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService){
        this.medicoService = medicoService;
    }

@GetMapping ("/consultar")
public ResponseEntity<List<Medico>> getList(){
    return ResponseEntity.ok(medicoService.getAll());
    }

@PostMapping("/registrar")
public ResponseEntity<Medico> create(@RequestBody MedicoDto medicoJson){
    Medico medico = new Medico();

        medico.setNombre_medico(medicoJson.getNombre_medico());
        medico.setApellido_medico(medicoJson.getApellido_medico());
        medico.setTelefono(medicoJson.getTelefono());
        medico.setId_especialidad(medicoJson.getId_especialidad());
        medico.setId_usuario(medicoJson.getId_usuario());
        medico.setJornada(medicoJson.getJornada());
        medico.setCod_jefe_inmediato(medicoJson.getCod_jefe_inmediato());

        medico.setCentro_hospitalario(medicoJson.getCentro_hospitalario());
        medico.setColegiado(medicoJson.getColegiado());
        medico.setFechaCreacion(medicoJson.getFechaCreacion());
        medico.setDireccion(medico.getDireccion());
        medico.setEdad(medicoJson.getEdad());
        medico.setObservaciones(medicoJson.getObservaciones());
        
        Medico reponseOfService = medicoService.create(medico);
        URI Location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(reponseOfService.getId_medico())
                .toUri();
        return ResponseEntity.created(Location).body(reponseOfService);
    }

    }