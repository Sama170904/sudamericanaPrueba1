package com.example.crudrapido.controller;

import com.example.crudrapido.dto.ParametroDTO;
import com.example.crudrapido.service.ParametroService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController // Spring sabe que esto maneja endpoints
@RequestMapping("/api/parametros")
public class ParametrosController {
    
    @Autowired
    private ParametroService parametroService;

    @PostMapping
        public String ajustarHora(@Valid @RequestBody ParametroDTO dto) { // @Valid activa la validación
            parametroService.actualizarHoraLimite(dto.getNuevaHora());
            return "Hora límite actualizada correctamente a: " + dto.getNuevaHora();
        }
}