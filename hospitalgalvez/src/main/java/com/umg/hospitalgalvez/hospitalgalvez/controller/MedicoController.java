package com.umg.hospitalgalvez.hospitalgalvez.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.umg.hospitalgalvez.hospitalgalvez.dto.MedicoDto;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Medico;
import com.umg.hospitalgalvez.hospitalgalvez.services.MedicoService;

@RestController
@RequestMapping ("/medico")

public class MedicoController {
    
    @Autowired
    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService){
        this.medicoService = medicoService;
    }

@GetMapping ("/medicos")
public ResponseEntity<List<Medico>> getAllMedicos(){
    List<Medico> medicos = medicoService.getAll();
    return new ResponseEntity<>(medicos, HttpStatus.OK);
    }

@GetMapping ("/medicos/Id")
public ResponseEntity<MedicoDto> getMedicoById(@PathVariable Long id){
    Medico medico = medicoService.getById(id);
    if (medico !=null) {
        MedicoDto medicoDto = new MedicoDto(medico.getId_medico(), medico.getNombre_medico(), medico.getApellido_medico(), 
        medico.getId_especialidad(), medico.getId_usuario(), medico.getTelefono(), 
        medico.getJornada(), medico.getCod_jefe_inmediato());        

        return new ResponseEntity<>(medicoDto, HttpStatus.OK);
    } else{
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
}