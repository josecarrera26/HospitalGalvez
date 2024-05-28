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
        // Autenticar al usuario
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
    
        // Obtener el UserDetails del usuario autenticado
        UserDetails userDetails = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
    
        // Generar el token JWT utilizando el UserDetails obtenido
        String token = jwtservice.getToken(userDetails);
    
        String rol = userDetails.getAuthorities().iterator().next().getAuthority(); // Ejemplo, podrías obtener más datos aquí si es necesario
        String username = userDetails.getUsername();
        // Retornar la respuesta con el token JWT
        return AuthResponse.builder()
        .token(token)
        .role(rol)
        .username(username)
        .build();
    }


}
