package com.umg.hospitalgalvez.hospitalgalvez.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umg.hospitalgalvez.hospitalgalvez.Jwt.AuthService;
import com.umg.hospitalgalvez.hospitalgalvez.dto.AuthResponse;
import com.umg.hospitalgalvez.hospitalgalvez.dto.LoginRequest;
import com.umg.hospitalgalvez.hospitalgalvez.dto.UsuarioDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    @Autowired
    private final AuthService authservice;

    // constrolador de autenticacion de login
    @PostMapping(value = "/token")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authservice.login(request));
    }

}
