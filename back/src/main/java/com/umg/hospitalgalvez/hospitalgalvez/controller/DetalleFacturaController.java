package com.umg.hospitalgalvez.hospitalgalvez.controller;


import com.umg.hospitalgalvez.hospitalgalvez.entity.DetalleFactura;
import com.umg.hospitalgalvez.hospitalgalvez.services.DetalleFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("detallefacturas")
public class DetalleFacturaController {

    private final DetalleFacturaService detalleFacturaService;

    
    
    @Autowired
    public DetalleFacturaController(DetalleFacturaService detalleFacturaService) {
        this.detalleFacturaService = detalleFacturaService;
    }




    @GetMapping
    public ResponseEntity <List<DetalleFactura>> getAll(){
        List<DetalleFactura> respuesta = detalleFacturaService.getAll();
        if (respuesta != null) {
            return ResponseEntity.ok(respuesta);
        }
        return  ResponseEntity.notFound().build();
    }
}
