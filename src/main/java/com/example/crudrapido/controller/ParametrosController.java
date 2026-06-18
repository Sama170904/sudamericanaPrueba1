package com.example.crudrapido.controller;

import com.example.crudrapido.service.ParametroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController // Spring sabe que esto maneja endpoints
@RequestMapping("/api/parametros")
public class ParametrosController {
    
    @Autowired
    private ParametroService parametroService;

    @PostMapping("/ajustar-hora")
    public String ajustarHora(@RequestParam String nuevaHora) {
        parametroService.actualizarHoraLimite(nuevaHora);
        return "Hora límite actualizada a: " + nuevaHora;
    }
}