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
@NoArgsConstructor  //no me funcioba builder sin esto
@AllArgsConstructor //No me funcona builder sin esto
@Data
public class Parametros {
    @Id
    private String clave; 
    private String valor; 
}
