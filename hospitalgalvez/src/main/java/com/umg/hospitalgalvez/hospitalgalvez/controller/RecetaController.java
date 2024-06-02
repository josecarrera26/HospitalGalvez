package com.umg.hospitalgalvez.hospitalgalvez.controller;

import com.umg.hospitalgalvez.hospitalgalvez.dto.EditarReceta;
import com.umg.hospitalgalvez.hospitalgalvez.dto.RecetaDto;
import com.umg.hospitalgalvez.hospitalgalvez.dto.RecetaDtoConsultaMed;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Cita;
import com.umg.hospitalgalvez.hospitalgalvez.entity.DetalleReceta;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Medicamento;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Receta;
import com.umg.hospitalgalvez.hospitalgalvez.services.CitaService;
import com.umg.hospitalgalvez.hospitalgalvez.services.DetalleRecetaService;
import com.umg.hospitalgalvez.hospitalgalvez.services.MedicamentoService;
import com.umg.hospitalgalvez.hospitalgalvez.services.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("recetas")
@PreAuthorize("hasAuthority('administrador') or hasAuthority('doctor') ")
public class RecetaController {
    @Autowired

    private final RecetaService recetaService;
    private final CitaService citaService;
    private final MedicamentoService medicamentoService;
    private final DetalleRecetaService detalleRecetaService;


    public RecetaController(RecetaService recetaService, CitaService citaService,
            DetalleRecetaService detalleRecetaService, MedicamentoService medicamentoService) {
        this.recetaService = recetaService;
        this.citaService = citaService;
        this.medicamentoService = medicamentoService;
        this.detalleRecetaService = detalleRecetaService;

    }

    @PostMapping
    public ResponseEntity<Receta> save(@RequestBody RecetaDto recetaJson) {

        final Optional<Cita> cita;
        cita = citaService.findById(recetaJson.getId_cita());

        // save
        Receta receta = new Receta();
        if (cita.isPresent()) {
            receta.setCita(cita.get());
            receta.getCita().setId_cita(recetaJson.getId_cita());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        List<DetalleReceta> detalles = new ArrayList<>();

        for (int x = 0; x < recetaJson.getId_medicamento().length; x++) {
            DetalleReceta det = new DetalleReceta();

            final Optional<Medicamento> meOptional = medicamentoService.findById(recetaJson.getId_medicamento()[x]);
            if (meOptional.isPresent()) {
                det.setMedicamento(meOptional.get());
            }
            det.setDescripcion(recetaJson.getDescripcion()[x]);
            detalles.add(det);
        }

        Receta recetaObj = new Receta();
        if (!detalles.isEmpty()) {
            recetaObj = recetaService.create(receta, detalles);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(recetaObj.getId_receta())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<RecetaDtoConsultaMed>> getRecetas() {
        List<RecetaDtoConsultaMed> response = recetaService.getrecetas();
        if (response.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/{id_receta}/{id_medico}")
    public ResponseEntity<List<RecetaDtoConsultaMed>> getRecetasMed(@PathVariable Long id_receta,
            @PathVariable Long id_medico) {
       
            List<RecetaDtoConsultaMed> response = recetaService.getrecetaByMedico(id_receta);
        if (response.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(response);
    }
}

    @GetMapping("/{id_receta}/{id_medico}/{id}")
    public ResponseEntity<List<EditarReceta>> getRecetaEditar(@PathVariable Long id_receta) {
           
            List<EditarReceta> response = detalleRecetaService.geteditarReceta(id_receta);

        if (response.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody RecetaDto recetajson) {
        Receta receta = new Receta();
        // buscar la receta y obtener la cita
        final Optional<Receta> recOptional;
        recOptional = recetaService.findById(id);

        final Optional<Cita> cita;
        cita = citaService.findById(recOptional.get().getCita().getId_cita());
        receta.setId_receta(recOptional.get().getId_receta());
        receta.setCita(cita.get());

        List<DetalleReceta> detalles = new ArrayList<>();

        for (int x = 0; x < recetajson.getId_medicamento().length; x++) {
            DetalleReceta det = new DetalleReceta();

            final Optional<Medicamento> meOptional = medicamentoService.findById(recetajson.getId_medicamento()[x]);
            if (meOptional.isPresent()) {
                det.setMedicamento(meOptional.get());
            }
            det.setDescripcion(recetajson.getDescripcion()[x]);
            detalles.add(det);
        }

        Receta recetaobj = recetaService.update(receta, detalles);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(recetaobj.getId_receta())
                .toUri();
        return ResponseEntity.ok(location);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Receta>> getById(@PathVariable Long id) {
        final Optional<Receta> med;
        med = recetaService.findById(id);
        if (med.isPresent()) {
            return ResponseEntity.ok(med);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
