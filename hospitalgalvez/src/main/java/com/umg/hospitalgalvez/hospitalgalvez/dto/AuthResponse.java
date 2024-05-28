package com.umg.hospitalgalvez.hospitalgalvez.dto;

import org.springframework.security.core.userdetails.UserDetails;

import com.umg.hospitalgalvez.hospitalgalvez.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
        //para responser al login o registro con el token
        String token;
        String role;
        String username;



}
