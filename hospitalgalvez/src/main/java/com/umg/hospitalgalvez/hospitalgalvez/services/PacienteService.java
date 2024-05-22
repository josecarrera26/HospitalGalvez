package com.umg.hospitalgalvez.hospitalgalvez.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Paciente;
import com.umg.hospitalgalvez.hospitalgalvez.repository.PacienteRepository;

@Service
public class PacienteService {
    @Autowired
    private final PacienteRepository pacienterepository;

    public PacienteService(PacienteRepository prepository){
        this.pacienterepository = prepository;
    }

    public List<Paciente> getAll(){
        return pacienterepository.findAll();
    }

    public Optional<Paciente> findById(Long id){
        return pacienterepository.findById(id);
    }

    public Paciente create(Paciente paciente){
        return pacienterepository.save(paciente);
    }

    public boolean delete(Long id){
        try{
            pacienterepository.deleteById(id);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public Paciente update(Paciente paciente){
        return pacienterepository.save(paciente);
    }

}
