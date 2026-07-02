package com.example.crudrapido.dto;

import com.example.crudrapido.dto.StudentDTO.OnCreate;
import com.example.crudrapido.dto.StudentDTO.OnUpdate;
import com.example.validation.ValidEmailDominio;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe ser un formato de correo válido")
    @ValidEmailDominio(message = "Debe ser del dominio sasf")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;
}