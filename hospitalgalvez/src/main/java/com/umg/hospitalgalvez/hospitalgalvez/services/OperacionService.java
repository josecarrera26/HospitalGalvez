package com.umg.hospitalgalvez.hospitalgalvez.services;

import com.umg.hospitalgalvez.hospitalgalvez.entity.Operacion;
import com.umg.hospitalgalvez.hospitalgalvez.repository.OperacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Operacion> findById(Long id){
    return operacionRepository.findById(id);
    }

    public boolean delete(Long id){
        try {
            operacionRepository.deleteById(id);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public Operacion update(Operacion operacion){
        return operacionRepository.save(operacion);
    }

    public Operacion save(Operacion operacion){
        return operacionRepository.save(operacion);
    }
}
