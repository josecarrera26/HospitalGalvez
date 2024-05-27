package com.umg.hospitalgalvez.hospitalgalvez.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.umg.hospitalgalvez.hospitalgalvez.entity.ParametroConsultaLegado;
import com.umg.hospitalgalvez.hospitalgalvez.repository.ParametroConsultaLegadoRepository;

@Service
public class ParametroConsultaLegadoService {

    @Autowired
    private ParametroConsultaLegadoRepository repository;

    public String[] getCorreoYPassword() {
        ParametroConsultaLegado parametro = repository.findByIdParametroConsultaLegado(1L);
        if (parametro != null) {
            System.out.println("parametro de busqueda");
            System.out.println(parametro);
            return parametro.getValidadorParametroConsultaLegado().split(",");
        }
        return null;
    }
}