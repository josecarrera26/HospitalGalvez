package com.umg.hospitalgalvez.hospitalgalvez.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Medico;
import com.umg.hospitalgalvez.hospitalgalvez.repository.MedicoRepository;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    @Autowired
    public MedicoService(MedicoRepository medicoRepository){
        this.medicoRepository = medicoRepository;
    }

    public Medico getById(Long id){
        Optional<Medico> medicoOptional = medicoRepository.findById(id);
        if (medicoOptional.isPresent()){
            return medicoOptional.get();
        }else{
            return null;
        }
    }

    public List<Medico> getAll(){
        return medicoRepository.findAll();
    }
}