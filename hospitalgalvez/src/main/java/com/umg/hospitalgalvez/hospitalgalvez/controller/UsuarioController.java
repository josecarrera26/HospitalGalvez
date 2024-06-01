package com.umg.hospitalgalvez.hospitalgalvez.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.umg.hospitalgalvez.hospitalgalvez.Jwt.AuthService;
import com.umg.hospitalgalvez.hospitalgalvez.dto.AuthResponse;
import com.umg.hospitalgalvez.hospitalgalvez.dto.UsuarioDto;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Usuario;
import com.umg.hospitalgalvez.hospitalgalvez.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
// @PreAuthorize("hasAuthority('administrador')")
public class UsuarioController {

    // @PreAuthorize("hasAuthority('administrador') or hasAuthority('secretaria')")
    @Autowired
    private final UsuarioService usuarioService;

    @Autowired
    private final AuthService authservice;

    public UsuarioController(UsuarioService usuarioService, AuthService authservice) {
        this.usuarioService = usuarioService;
        this.authservice = authservice;
    }

    // @PreAuthorize("hasRole('ROLE_administrador')")
    @GetMapping("/consulta")
    public ResponseEntity<List<Usuario>> getList() {
        System.out.println(" ingreso al controlador consulta");
        return ResponseEntity.ok(usuarioService.getAll());

    }

    // para registrar usuario
    @PostMapping(value = "/registrar")
    public ResponseEntity<AuthResponse> register(@RequestBody UsuarioDto request) {
        return ResponseEntity.ok(authservice.register(request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        usuarioService.deleteUser(id);
        System.out.println("Usuario" + id);

        return ResponseEntity.noContent().build();

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AuthResponse> updateUser(@PathVariable Long id, @RequestBody UsuarioDto request) {
        AuthResponse response = authservice.updateUser(id, request);
        return ResponseEntity.ok(response);
    }

    /*
     * 
     * @CrossOrigin(origins = "http://localhost:4200")
     * 
     * @PostMapping(value = "/login")
     * public ResponseEntity<Optional<Usuario>> login(@RequestBody UsuarioDto
     * usuarioJson) {
     * Optional<Usuario> usuarioOpt =
     * usuarioService.findByUsername(usuarioJson.getUsername());
     * if (usuarioOpt.isPresent()) {
     * 
     * Usuario usuario = usuarioOpt.get();
     * 
     * if (usuario.getPasswordUsuario().equals(usuarioJson.getPassword())) {
     * System.out.println("Inicio de sesion exitoso");
     * return ResponseEntity.ok(usuarioOpt);
     * 
     * } else {
     * // Log para contraseña incorrecta
     * System.out.println("Contraseña incorrecta para el usuario: " +
     * usuario.getUsername());
     * return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
     * }
     * } else {
     * // PENDIENTE DEL HTTPSTATUS
     * // Log para usuario no encontrado
     * System.out.println("Usuario no encontrado: " + usuarioJson.getUsername());
     * return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
     * }
     * }
     */

    /*
     * @PostMapping("/registro")
     * public ResponseEntity<Usuario> create(@RequestBody UsuarioDto usuarioJson) {
     * Usuario usuario = new Usuario();
     * 
     * usuario.setUsername(usuarioJson.getUsername());
     * 
     * usuario.setRole(usuarioJson.getRole());
     * usuario.setPasswordUsuario(usuarioJson.getPassword());
     * 
     * Usuario reponseOfService = usuarioService.create(usuario);
     * URI Location = ServletUriComponentsBuilder
     * .fromCurrentRequest()
     * .path("/{id}")
     * .buildAndExpand(reponseOfService.getIdUsuario())
     * .toUri();
     * return ResponseEntity.created(Location).body(reponseOfService);
     * }
     */

}
