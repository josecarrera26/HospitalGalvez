package com.umg.hospitalgalvez.hospitalgalvez.services;

import com.umg.hospitalgalvez.hospitalgalvez.entity.Cita;
import com.umg.hospitalgalvez.hospitalgalvez.entity.DetalleReceta;
import com.umg.hospitalgalvez.hospitalgalvez.entity.Medicamento;
import com.umg.hospitalgalvez.hospitalgalvez.repository.DetalleRecetaRepository;
import com.umg.hospitalgalvez.hospitalgalvez.repository.MedicamentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleRecetaService {

    private final DetalleRecetaRepository detalleRecetaRepository;
    private final MedicamentoService medicamentoService;


    @Autowired
    public DetalleRecetaService(DetalleRecetaRepository detalleRecetaRepository, MedicamentoService medicamentosService) {
        this.detalleRecetaRepository = detalleRecetaRepository;
        this.medicamentoService = medicamentosService;
    }

    @Transactional
   public DetalleReceta create(DetalleReceta detreceta) {
     final Optional<Medicamento> med;
        med = medicamentoService.findById(detreceta.getMedicamento().getId_medicamento());


    DetalleReceta det = new DetalleReceta();
    det.setMedicamento(med.get());
        return detalleRecetaRepository.save(det);
    }

    public List<DetalleReceta> getAll() {
        return detalleRecetaRepository.findAll();
    }

    public DetalleReceta update(DetalleReceta detreceta) {
        return detalleRecetaRepository.save(detreceta);
    }

    public Optional<DetalleReceta> findById(Long idmed) {
        return detalleRecetaRepository.findById(idmed);
    }
}
