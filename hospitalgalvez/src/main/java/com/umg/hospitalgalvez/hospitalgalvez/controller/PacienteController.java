package com.umg.hospitalgalvez.hospitalgalvez.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.umg.hospitalgalvez.hospitalgalvez.dto.MedicamentoDto;
import com.umg.hospitalgalvez.hospitalgalvez.dto.PacienteDto;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Medicamento;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Paciente;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Usuario;
import com.umg.hospitalgalvez.hospitalgalvez.services.PacienteService;
import com.umg.hospitalgalvez.hospitalgalvez.services.ParametroConsultaLegadoService;
import com.umg.hospitalgalvez.hospitalgalvez.services.UsuarioService;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private final PacienteService pacienteService;
    private final UsuarioService usuarioService;
    private final RestTemplate restTemplate;
    private ParametroConsultaLegadoService parametroService;

    public PacienteController(PacienteService pacienteService, UsuarioService usuarioService,
                              RestTemplate restTemplate, ParametroConsultaLegadoService parametroService) {
        this.pacienteService = pacienteService;
        this.usuarioService = usuarioService;
        this.restTemplate = restTemplate;
        this.parametroService = parametroService;
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> getAll() {
        List<Paciente> respuesta = pacienteService.getAll();
        if (respuesta != null) {
            return ResponseEntity.ok(respuesta);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Paciente>> getById(@PathVariable Long id){
        final Optional<Paciente> med;
        med = pacienteService.findById(id);
        if (med.isPresent()) {
            return ResponseEntity.ok(med);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Paciente> save(@RequestBody PacienteDto pacienteJson) {

        final Optional<Usuario> usuario;
        usuario = usuarioService.findById(pacienteJson.getId_usuario());

        String nit = pacienteJson.getNit();
        String dpi = pacienteJson.getDpi();
        String url;

        if (nit != "") {
            url = "http://143.198.49.228:8080/API_WS_PROCESOS_WAY/webresources/Validar_NIT";
        } else {
            url = "http://143.198.49.228:8080/API_WS_PROCESOS_WAY/webresources/Validar_CUI";
        }

        String[] correoYPassword = parametroService.getCorreoYPassword();

        System.err.println("consulta");
        System.err.println(correoYPassword);

        String correo = correoYPassword[0];
        String password = correoYPassword[1];

        HttpHeaders headers = new HttpHeaders();
        headers.set("correo", correo);
        headers.set("password", password);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponentsBuilder builder;
        if (nit != "") {
            builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("nit_cliente", nit);
        } else {
            builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("dpi_cliente", dpi);
        }

        ResponseEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            String responseBody = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode rootNode = objectMapper.readTree(responseBody);
                String idError = rootNode.path("id_error").asText();

                if (!"1".equals(idError)) {
                    return ResponseEntity.badRequest().body(null);
                }

                JsonNode dataUsuario = rootNode.path("data_usuario").get(0);

                String nombreCliente;
                if (nit != "") {
                    nombreCliente = dataUsuario.path("nombre_nit_cliente").asText();
                } else {
                    nombreCliente = dataUsuario.path("nombre_dpi_cliente").asText();
                }

                Paciente paciente = new Paciente();
                paciente.setId_paciente(pacienteJson.getId_paciente());
                paciente.setNombre(nombreCliente);
                paciente.setFecha_nacimiento(pacienteJson.getFecha_nacimiento());
                paciente.setDireccion(pacienteJson.getDireccion());
                paciente.setTelefono(pacienteJson.getTelefono());
                paciente.setDpi(pacienteJson.getDpi());
                paciente.setNit(pacienteJson.getNit());
                paciente.setEmail(pacienteJson.getEmail());
                paciente.setGenero(pacienteJson.getGenero());
                paciente.setEstado(pacienteJson.getEstado());
                paciente.setUsuario(usuario.get());

                Paciente e = pacienteService.create(paciente);

                URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(e.getId_paciente())
                        .toUri();

                return ResponseEntity.created(location).build();
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        final Optional<Paciente> paciente;
        paciente = pacienteService.findById(id);

        boolean respuesta = pacienteService.delete(id);
        if (respuesta) {
            return ResponseEntity.ok(paciente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
