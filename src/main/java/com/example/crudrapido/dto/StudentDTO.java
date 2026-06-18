package com.example.crudrapido.dto;

import com.example.validation.ValidEmailDominio;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@NoArgsConstructor // me dio un error el swagger por falta de cosntructor en el dto y tuve que poenr esto 
@AllArgsConstructor
public class StudentDTO {

    public interface OnCreate {}
    public interface OnUpdate {}

    @NotBlank(groups = OnUpdate.class)
    @Null(groups = OnCreate.class)
    private Long studentId;

    @Size(min = 2, max = 15, message = "El nombre debe tener entre 2 y 15 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+$", message = "El nombre no debe contener espacios ni números")
    @NotBlank(groups = {OnCreate.class})
    private String firstName;

    @Size(min = 2, max = 15, message = "El apellido debe tener entre 2 y 15 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+$", message = "El apellido no debe contener espacios ni números")
    @NotBlank(groups = {OnCreate.class})
    private String lastName;
    
    @NotBlank(groups = {OnCreate.class})
    @Email(groups = {OnCreate.class, OnUpdate.class})
    @ValidEmailDominio(groups = {OnCreate.class, OnUpdate.class})
    private String email;


}



