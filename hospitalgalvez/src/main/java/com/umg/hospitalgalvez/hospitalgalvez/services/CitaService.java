package com.umg.hospitalgalvez.hospitalgalvez.services;

import com.umg.hospitalgalvez.hospitalgalvez.entity.Cita;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Medico;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Paciente;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Usuario;
import com.umg.hospitalgalvez.hospitalgalvez.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaService {
    private final CitaRepository citaRepository;

    @Autowired
    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    public Optional<Cita> findById(Long id){
        return citaRepository.findById(id);
    }

    public List<Cita> getAll(){
        return citaRepository.findAll();
    }

    public Cita create (Cita cita){
        return citaRepository.save(cita);
    }

    public List<Cita> getCitasPaciente(Long pacienteId){
        Paciente paciente = new Paciente();
        paciente.setId_paciente(pacienteId);
        return citaRepository.findByPaciente(paciente);
    }

    public List<Cita> getCitasMedico(Long medicoId){
        Medico medico = new Medico();
        medico.setId_medico(medicoId);
        return citaRepository.findByMedico(medico);
    }

    public List<Cita> getCitasUsuario(Long usuarioId){
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(usuarioId);
        return citaRepository.findByUsuario(usuario);
    }

}
