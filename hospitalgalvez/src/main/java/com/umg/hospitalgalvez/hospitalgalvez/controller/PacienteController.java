package com.umg.hospitalgalvez.hospitalgalvez.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umg.hospitalgalvez.hospitalgalvez.dto.PacienteDto;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Paciente;
import com.umg.hospitalgalvez.hospitalgalvez.services.PacienteService;

@RestController
@RequestMapping("pacientes")
public class PacienteController {
    @Autowired
    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService){
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public List<Paciente>getList(){
        return pacienteService.getAll();
    }

    @GetMapping("id")
    public ResponseEntity<Optional<Paciente>> getById(@PathVariable Long id){
        Optional<Paciente> responseOfService = pacienteService.getById(id);

        if (responseOfService.isPresent()) {
            ResponseEntity.ok(responseOfService);       
        }
        return ResponseEntity.notFound().build();
    }

    public Paciente create(@RequestBody PacienteDto pacienteJson){

        Paciente paciente = new Paciente();
        paciente.setNombre(pacienteJson.getNombre());
        paciente.setApellido(pacienteJson.getApellido());
        paciente.setFecha_nacimiento(pacienteJson.getFecha_nacimiento());
        paciente.setDirecicon(pacienteJson.getDirecicon());
        paciente.setTelefono(pacienteJson.getTelefono());
        paciente.setDpi(pacienteJson.getDpi());
        paciente.setNit(pacienteJson.getNit());
        paciente.setEmail(pacienteJson.getEmail());
        paciente.setGenero(pacienteJson.getGenero());
        paciente.setId_usuario(pacienteJson.getId_usuario());
        paciente.setEstado(pacienteJson.getEstado());

        return pacienteService.create(paciente);
    }  
}
