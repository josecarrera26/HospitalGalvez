package com.umg.hospitalgalvez.hospitalgalvez.controller;

import com.umg.hospitalgalvez.hospitalgalvez.dto.RecetaDto;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Cita;
import com.umg.hospitalgalvez.hospitalgalvez.entity.DetalleReceta;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Medicamento;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Receta;
import com.umg.hospitalgalvez.hospitalgalvez.services.CitaService;
import com.umg.hospitalgalvez.hospitalgalvez.services.DetalleRecetaService;
import com.umg.hospitalgalvez.hospitalgalvez.services.MedicamentoService;
import com.umg.hospitalgalvez.hospitalgalvez.services.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
public class RecetaController {

    private final RecetaService recetaService;
    private final CitaService citaService;
    private final DetalleRecetaService detalleRecetaService;
    private final MedicamentoService medicamentoService;

    @Autowired
    public RecetaController(RecetaService recetaService, CitaService citaService,
            DetalleRecetaService detalleRecetaService, MedicamentoService medicamentoService) {
        this.recetaService = recetaService;
        this.citaService = citaService;
        this.detalleRecetaService = detalleRecetaService;
        this.medicamentoService = medicamentoService;
    }

    @PostMapping
    public ResponseEntity<Receta> save(@RequestBody RecetaDto recetaJson) {

        final Optional<Cita> cita;
        System.out.println("HOLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"+ recetaJson.getId_cita() );
       // cita = citaService.findById(recetaJson.getId_cita());

        // save
        Receta receta = new Receta();
       // receta.setCita(cita.get());
       // receta.setFechaCreacion(recetaJson.getFecha());

       // receta.getCita().setId_cita(recetaJson.getId_cita());


        // SAVE DETALLE
        // array de lons de id medicamento
      //  Long[] id_medicamentodto = recetaJson.getId_medicamento();
        List<Medicamento> medicamentos = new ArrayList<>();
        // for para agregar los medicamentos al array
      //  for (Long ids : id_medicamentodto) {
     //       final Optional<Medicamento> med;
     //       med = medicamentoService.findById(ids);
     //       Medicamento medicamento = med.orElse(null);
     //       if (medicamento != null) {
     //           medicamentos.add(medicamento);
     //       }
     //   }

        Receta recetaObj = recetaService.create(receta);


        // obtener receta creada
      //  final Optional<Receta> recetaOp;
      //  recetaOp = recetaService.findById(recetaObj.getId_receta());

      //  List <DetalleReceta> detreceta = new ArrayList<>();
      //  for (Medicamento medicamento : medicamentos) {
     //       detreceta.add(new DetalleReceta(recetaObj,medicamento));
      //      System.out.println("ESTO HAY EN EL FOR"+    medicamento);
            //detreceta.getMedicamento().setId_medicamento(medicamento.getId_medicamento());
            //detreceta.getReceta().setId_receta(recetaObj.getId_receta());

          //  DetalleReceta detrecetaObj = detalleRecetaService.create(detreceta);
           // System.out.println("ESTO HAY EN DETALLE"+ detrecetaObj);
    //    }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(recetaObj.getId_receta())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<Receta>> getList() {
        List<Receta> response = recetaService.getAll();
        if (response.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(response);
        }
    }

    @PatchMapping()
    public ResponseEntity<Object> update(@RequestBody RecetaDto recetajson) {
        Receta receta = new Receta();
        final Optional<Cita> cita;
        cita = citaService.findById(recetajson.getId_cita());
        receta.setId_receta(recetajson.getId_receta());
        receta.setCita(cita.get());
        receta.setFechaCreacion(recetajson.getFecha());

        Receta recetaobj = recetaService.update(receta);
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
