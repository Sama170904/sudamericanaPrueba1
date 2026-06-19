package com.example.crudrapido.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ParametroDTO {


    @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$", message = "Formato inválido")
    private String nuevaHora;
    
    // Getters y Setters
}
