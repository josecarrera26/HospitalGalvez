package com.umg.hospitalgalvez.hospitalgalvez.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.umg.hospitalgalvez.hospitalgalvez.dto.PacienteDto;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Paciente;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Usuario;
import com.umg.hospitalgalvez.hospitalgalvez.services.PacienteService;
import com.umg.hospitalgalvez.hospitalgalvez.services.UsuarioService;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired
    private final PacienteService pacienteService;
    private final UsuarioService usuarioService;

    public PacienteController(PacienteService pacienteService, UsuarioService usuarioService){
        this.pacienteService = pacienteService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> getAll(){
        List<Paciente> respuesta = pacienteService.getAll();
        if (respuesta != null) {
            return ResponseEntity.ok(respuesta);
        }
        return  ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Paciente> save(@RequestBody PacienteDto pacienteJson){

        final Optional<Usuario> usuario;
        usuario = usuarioService.findById(pacienteJson.getId_usuario());

        Paciente paciente = new Paciente();

        paciente.setNombre(pacienteJson.getNombre());
        paciente.setApellido(pacienteJson.getApellido());
        paciente.setFecha_nacimiento(pacienteJson.getFecha_nacimiento());
        paciente.setDireccion(pacienteJson.getDireccion());
        paciente.setTelefono(pacienteJson.getTelefono());
        paciente.setDpi(pacienteJson.getDpi());
        paciente.setNit(pacienteJson.getNit());
        paciente.setEmail(pacienteJson.getEmail());
        paciente.setGenero(pacienteJson.getGenero());
        paciente.setEstado(pacienteJson.getEstado());
        paciente.setUsuario(usuario.get());

        paciente.getUsuario().setIdUsuario(pacienteJson.getId_usuario());

        Paciente e = pacienteService.create(paciente);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(e.getId_paciente())
            .toUri();

        return ResponseEntity.created(location).build();
    }  

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        final Optional<Paciente> paciente;
        paciente = pacienteService.findById(id);

        boolean respuesta = pacienteService.delete(id);
        if (respuesta){
            return ResponseEntity.ok(paciente);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
