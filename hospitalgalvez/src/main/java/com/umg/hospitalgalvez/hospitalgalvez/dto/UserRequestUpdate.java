package com.umg.hospitalgalvez.hospitalgalvez.dto;

import com.umg.hospitalgalvez.hospitalgalvez.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestUpdate {

    private String username;
    private Role role;
    private String password;

}
