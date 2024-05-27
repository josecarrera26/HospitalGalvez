package com.umg.hospitalgalvez.hospitalgalvez.services;
import com.umg.hospitalgalvez.hospitalgalvez.entity.DetalleReceta;
import com.umg.hospitalgalvez.hospitalgalvez.repository.DetalleRecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleRecetaService {
    @Autowired

    private final DetalleRecetaRepository detalleRecetaRepository;


    public DetalleRecetaService(DetalleRecetaRepository detalleRecetaRepository) {
        this.detalleRecetaRepository = detalleRecetaRepository;
    }

    @Transactional
   public DetalleReceta create(DetalleReceta detreceta) {
     
        return detalleRecetaRepository.save(detreceta);
    }

    public List<DetalleReceta> getAll() {
        return detalleRecetaRepository.findAll();
    }

    public DetalleReceta update(DetalleReceta detreceta) {
        return detalleRecetaRepository.save(detreceta);
    }

    public  boolean delete(Long idReceta) {
        try{
            detalleRecetaRepository.deleteByRecetaId(idReceta);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public Optional<DetalleReceta> findById(Long idmed) {
        return detalleRecetaRepository.findById(idmed);
    }
}
