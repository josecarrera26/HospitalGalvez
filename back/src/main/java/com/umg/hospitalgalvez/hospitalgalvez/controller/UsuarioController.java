package com.umg.hospitalgalvez.hospitalgalvez.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.umg.hospitalgalvez.hospitalgalvez.dto.UsuarioDto;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Usuario;
import com.umg.hospitalgalvez.hospitalgalvez.services.UsuarioService;

@RestController
@RequestMapping("/apirest")
public class UsuarioController {

    @Autowired
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/consulta")
    public ResponseEntity<List<Usuario>> getList() {
        return ResponseEntity.ok(usuarioService.getAll());

    }

    @PostMapping("/registro")
    public ResponseEntity<Usuario> create(@RequestBody UsuarioDto usuarioJson) {
        Usuario usuario = new Usuario();

        usuario.setUsername(usuarioJson.getUsername());
        
        usuario.setRole(usuarioJson.getRole());
        usuario.setPasswordUsuario(usuarioJson.getPassword());

        Usuario reponseOfService = usuarioService.create(usuario);
        URI Location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(reponseOfService.getIdUsuario())
                .toUri();
        return ResponseEntity.created(Location).body(reponseOfService);
    }

}
