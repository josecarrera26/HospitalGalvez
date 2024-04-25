package com.umg.hospitalgalvez.hospitalgalvez.services;

import com.umg.hospitalgalvez.hospitalgalvez.entity.Operacion;
import com.umg.hospitalgalvez.hospitalgalvez.repository.OperacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperacionService {


    private final OperacionRepository operacionRepository;

    @Autowired
    public OperacionService(OperacionRepository operacionRepository) {
        this.operacionRepository = operacionRepository;
    }

    public List<Operacion> getAll(){
        return operacionRepository.findAll();
    }

}
