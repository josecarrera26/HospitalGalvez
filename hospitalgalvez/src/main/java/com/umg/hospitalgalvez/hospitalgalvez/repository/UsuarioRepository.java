package com.umg.hospitalgalvez.hospitalgalvez.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umg.hospitalgalvez.hospitalgalvez.entity.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
