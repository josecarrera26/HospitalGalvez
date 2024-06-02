package com.umg.hospitalgalvez.hospitalgalvez.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.umg.hospitalgalvez.hospitalgalvez.dto.FacturaDto;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Cita;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Factura;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Medicamento;
import com.umg.hospitalgalvez.hospitalgalvez.services.CitaService;
import com.umg.hospitalgalvez.hospitalgalvez.services.DetalleFacturaService;
import com.umg.hospitalgalvez.hospitalgalvez.services.FacturaService;
import com.umg.hospitalgalvez.hospitalgalvez.services.MedicamentoService;
import com.umg.hospitalgalvez.hospitalgalvez.services.ParametroConsultaLegadoService;

@RestController
@RequestMapping("/facturas")
@PreAuthorize("hasAuthority('administrador') or hasAuthority('secretaria')")
public class FacturaController {

    @Autowired
    private final FacturaService facturaService;
    private final CitaService citaService;
    private final MedicamentoService medicamentoService;
    private ParametroConsultaLegadoService parametroService;
    private final RestTemplate restTemplate;

    public FacturaController(FacturaService facturaService, CitaService citaService,
            DetalleFacturaService detalleFacturaService, MedicamentoService medicamentoService,
            ParametroConsultaLegadoService parametroService, RestTemplate restTemplate) {
        this.facturaService = facturaService;
        this.citaService = citaService;
        this.medicamentoService = medicamentoService;
        this.parametroService = parametroService;
        this.restTemplate = restTemplate;
    }

    @PostMapping
    public ResponseEntity<Factura> save(@RequestBody FacturaDto facturaJson) {

        final Optional<Cita> cita;
        cita = citaService.findById(facturaJson.getId_cita());

        // save
        Factura factura = new Factura();
        if (cita.isPresent()) {
            factura.setCita(cita.get());
            factura.getCita().setId_cita(facturaJson.getId_cita());
            factura.getCita().setUsuario(facturaJson.getId_usuario());
            factura.setTotal(facturaJson.getTotal());

            if (facturaJson.getNit() != "") {

                String nit = facturaJson.getNit();

                String url = "http://143.198.49.228:8080/API_WS_PROCESOS_WAY/webresources/Validar_NIT";

                String[] correoYPassword = parametroService.getCorreoYPassword();

                System.err.println("consulta");

                String correo = correoYPassword[0];
                String password = correoYPassword[1];

                HttpHeaders headers = new HttpHeaders();
                headers.set("correo", correo);
                headers.set("password", password);

                HttpEntity<String> entity = new HttpEntity<>(headers);

                UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                        .queryParam("nit_cliente", nit);
                        

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

                        String nombreCliente = dataUsuario.path("nombre_nit_cliente").asText();

                        System.err.println(nombreCliente);

                        factura.setNit(facturaJson.getNit());

                    } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
            } else {
                factura.setNit("CF");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        List<Medicamento> medicamentos = List.of(medicamentoService.findById(1L)
                .orElseThrow(() -> new RuntimeException("Medicamento not found")));

        Factura persistedFactura = facturaService.create(factura, medicamentos);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(persistedFactura.getId_factura())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<Factura>> getList() {
        List<Factura> response = facturaService.getAll();
        if (response.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(response);
        }
    }

    @PatchMapping()
    public ResponseEntity<Object> update(@RequestBody FacturaDto facturajson) {
        Factura factura = new Factura();
        final Optional<Cita> cita;
        cita = citaService.findById(facturajson.getId_cita());
        factura.setId_factura(facturajson.getId_factura());
        factura.setCita(cita.get());
        factura.setFechaCreacion(facturajson.getFecha_factura());

        Factura facturaobj = facturaService.update(factura);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(facturaobj.getId_factura())
                .toUri();
        return ResponseEntity.ok(location);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Factura>> getById(@PathVariable Long id) {
        final Optional<Factura> med;
        med = facturaService.findById(id);
        if (med.isPresent()) {
            return ResponseEntity.ok(med);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
