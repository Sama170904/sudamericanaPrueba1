package com.example.crudrapido.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor  // <--- ESTA ANOTACIÓN ES LA QUE SOLUCIONA TU ERROR
@AllArgsConstructor // <--- Necesaria para que @Builder funcione bien
@Data
public class Parametros {
    @Id
    private String clave; 
    private String valor; 
}
