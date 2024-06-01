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

    // para login
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(UsuarioDto request) {

        // crear objetos usando builder
        Usuario user = Usuario.builder()
                .username(request.getUsername())
                .role(request.getRole()) // pendiente de esto
                .passwordUsuario(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
        return AuthResponse.builder()
                .token(jwtservice.getToken(user))
                .build();
    }

    // update user
    public AuthResponse updateUser(Long userId, UsuarioDto request) {
        Usuario user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        // Update user details
        if (request.getUsername() != null) {
            user.setUsername(request.getUsername());
        }

        if (request.getRole() != null) {
            user.setRole(request.getRole());
        }
        if (request.getPassword() != null) {
            user.setPasswordUsuario(passwordEncoder.encode(request.getPassword()));
        }

        userRepository.save(user);

        // Generate a new token in case roles or other security details changed
        String token = jwtservice.getToken(user);

        // Return the response
        return AuthResponse.builder()
                .token(token)
                .role(user.getRole().getNombre()) // pendiente de esto
                .username(user.getUsername())
                .build();
    }

    public AuthResponse login(LoginRequest request) {
        // Autenticar al usuario
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        // Obtener el UserDetails del usuario autenticado
        UserDetails userDetails = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Generar el token JWT utilizando el UserDetails obtenido
        String token = jwtservice.getToken(userDetails);

        String rol = userDetails.getAuthorities().iterator().next().getAuthority(); // Ejemplo, podrías obtener más
                                                                                    // datos aquí si es necesario
        String username = userDetails.getUsername();
        // Retornar la respuesta con el token JWT
        return AuthResponse.builder()
                .token(token)
                .role(rol)
                .username(username)
                .build();
    }

}
