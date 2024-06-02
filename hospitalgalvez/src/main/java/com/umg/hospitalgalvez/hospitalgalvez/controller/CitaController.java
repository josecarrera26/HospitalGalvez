package com.umg.hospitalgalvez.hospitalgalvez.controller;

import com.umg.hospitalgalvez.hospitalgalvez.dto.CitaDto;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Cita;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Medico;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Paciente;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Usuario;
import com.umg.hospitalgalvez.hospitalgalvez.services.CitaService;
import com.umg.hospitalgalvez.hospitalgalvez.services.MedicoService;
import com.umg.hospitalgalvez.hospitalgalvez.services.PacienteService;
import com.umg.hospitalgalvez.hospitalgalvez.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("citas")
@PreAuthorize("hasAuthority('administrador') or hasAuthority('secretaria') or hasAuthority('doctor') ")
public class CitaController {

    private final CitaService citaService;
    private final MedicoService medicoService;
    private final PacienteService pacienteService;
    private final UsuarioService usuarioService;

    @Autowired
    public CitaController(CitaService citaService, MedicoService medicoService, PacienteService pacienteService, UsuarioService usuarioService) {
        this.citaService = citaService;
        this.medicoService = medicoService;
        this.pacienteService = pacienteService;
        this.usuarioService = usuarioService;
    }


    @GetMapping
    public ResponseEntity<List<Cita>> getAll() {
        List<Cita> respuesta = new ArrayList<>();
        respuesta = citaService.getAll();
        if (respuesta != null) {
            return ResponseEntity.ok(respuesta);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Cita>> getCitaById(@PathVariable Long id) {
        final Optional<Cita> respuesta;
        respuesta = citaService.findById(id);
        if (respuesta.isPresent()) {
            return ResponseEntity.ok(respuesta);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public  ResponseEntity<Cita> createCita(@RequestBody CitaDto citaJson) {

//        return ResponseEntity.ok(citaJson);
        Cita envio = new Cita();
        final Cita respuesta;
        final Optional<Paciente> paciente;
        final Optional<Usuario> usuario;
        final Medico medico;

        paciente = pacienteService.findById(citaJson.getId_paciente());
        usuario =  usuarioService.findById(citaJson.getId_usuario());
        medico = medicoService.getById(citaJson.getId_medico());
        System.out.println(citaJson.getFecha_cita());

        envio.setFecha_cita(citaJson.getFecha_cita());
        envio.setPaciente(paciente.get());
        envio.setUsuario(usuario.get());
        envio.setEstado(citaJson.getEstado());
        envio.setDescripcion(citaJson.getDescripcion());
        envio.setMedico(medico);


        respuesta  = citaService.create(envio);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/bypaciente/{id}")
    public ResponseEntity<List<Cita>> getCitaByPaciente(@PathVariable Long id) {
        Optional<Paciente> paciente = pacienteService.findById(id);
        if (paciente.isPresent()) {
            List<Cita> respuesta = citaService.getCitasPaciente(id);
            if (!respuesta.isEmpty()) {
                return ResponseEntity.ok(respuesta);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/citasbymedico/{id}")
    public ResponseEntity<List<Cita>> getCitaByMedico(@PathVariable Long id) {
        Medico medico = medicoService.getById(id);
        if (medico != null) {
            List<Cita> respuesta = citaService.getCitasMedico(id);
            if (!respuesta.isEmpty()) {
                return ResponseEntity.ok(respuesta);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/citasbyusuario/{id}")
    public ResponseEntity<List<Cita>> getCitaByUsuario(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.findById(id);
        if (usuario.isPresent()) {
            List<Cita> respuesta = citaService.getCitasUsuario(id);
            if (!respuesta.isEmpty()) {
                return ResponseEntity.ok(respuesta);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/update")
    public ResponseEntity<Cita> updateCita(@RequestBody CitaDto citaJson) {
        final Cita citaEntity = null;
        citaEntity.setId_cita(citaJson.getId_cita());
        citaEntity.setFecha_cita(citaJson.getFecha_cita());
        citaEntity.setEstado(citaJson.getEstado());
        citaEntity.setDescripcion(citaJson.getDescripcion());
        citaEntity.setMedico(medicoService.getById(citaJson.getId_medico()));

        citaService.create(citaEntity);

        return ResponseEntity.ok(citaEntity);
    }

//    @GetMapping("/bydate")
//    public ResponseEntity<List<Cita>> byDate(){
//        List<Cita> respuesta = new ArrayList<>();
//        respuesta = citaService.getCitasOrderbByDate();
//        if (respuesta != null) {
//            return ResponseEntity.ok(respuesta);
//        }
//        return ResponseEntity.notFound().build();
//    }
}
