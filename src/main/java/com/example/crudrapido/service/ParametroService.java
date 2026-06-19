package com.example.crudrapido.service;

import com.example.crudrapido.entity.Parametros;
import com.example.crudrapido.repository.ParametroRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalTime;

@Service
public class ParametroService {
    
    @Autowired
    private ParametroRepository repository;

    public LocalTime getHoraLimite() {
        return repository.findById("hora_limite")
                .map(p -> LocalTime.parse(p.getValor()))
                .orElse(LocalTime.of(23, 59));
        }
        
        public void actualizarHoraLimite(String nuevaHora) {
            Parametros p = repository.findById("hora_limite")
                    .orElse(Parametros.builder().clave("hora_limite").build()); 
            
            p.setValor(nuevaHora);
            repository.save(p);
        }
}