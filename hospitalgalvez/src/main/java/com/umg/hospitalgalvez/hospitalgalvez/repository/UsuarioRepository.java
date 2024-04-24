package com.umg.hospitalgalvez.hospitalgalvez.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.umg.hospitalgalvez.hospitalgalvez.entity.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
