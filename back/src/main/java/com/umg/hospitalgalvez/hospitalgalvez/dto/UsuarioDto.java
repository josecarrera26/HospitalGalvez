package com.umg.hospitalgalvez.hospitalgalvez.dto;

import com.umg.hospitalgalvez.hospitalgalvez.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {

    private Long idUsuario;
    private String username;
    private Role Role;
    private String password;

}
