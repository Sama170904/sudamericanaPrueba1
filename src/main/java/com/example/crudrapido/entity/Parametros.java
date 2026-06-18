package com.example.crudrapido.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Entity
@Builder
@Data
public class Parametros {
    @Id
    private String clave; 
    private String valor; 
}
