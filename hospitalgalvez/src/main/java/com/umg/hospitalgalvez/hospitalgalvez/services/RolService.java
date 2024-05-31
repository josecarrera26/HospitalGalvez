package com.umg.hospitalgalvez.hospitalgalvez.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umg.hospitalgalvez.hospitalgalvez.entity.Role;
import com.umg.hospitalgalvez.hospitalgalvez.repository.RoleRepository;


@Service
public class RolService {

        @Autowired
    private final RoleRepository roleRepository;

        public RolService(RoleRepository roleRepository) {
            this.roleRepository = roleRepository;
        }
        
        public List<Role> getAll() {
        return roleRepository.findAll();
    }

    

}
