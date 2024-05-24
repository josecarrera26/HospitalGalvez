package com.umg.hospitalgalvez.hospitalgalvez.Jwt;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.umg.hospitalgalvez.hospitalgalvez.dto.AuthResponse;
import com.umg.hospitalgalvez.hospitalgalvez.dto.LoginRequest;
import com.umg.hospitalgalvez.hospitalgalvez.dto.UsuarioDto;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Usuario;
import com.umg.hospitalgalvez.hospitalgalvez.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsuarioRepository userRepository;
    private final JwtService jwtservice;
    private final PasswordEncoder passwordEncoder;

    
        //para login
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(UsuarioDto request) {

        // crear objetos usando builder
        Usuario user = Usuario.builder()
                .username(request.getUsername())
                .role(request.getRole())  //pendiente de esto
                .passwordUsuario(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
        return AuthResponse.builder()
                .token(jwtservice.getToken(user))
                .build();
    }

    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtservice.getToken(user);
        return AuthResponse.builder()
        .token(token)
        .build();
        
    }


}
