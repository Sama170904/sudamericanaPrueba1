package com.example.crudrapido.dto;

import java.util.HashSet;
import java.util.Set;

import com.example.crudrapido.entity.Course;
import com.example.validation.ValidEmailDominio;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Schema(hidden = true)
@Data
@Builder
@NoArgsConstructor // me dio un error el swagger por falta de cosntructor en el dto y tuve que poenr esto 
@AllArgsConstructor
public class StudentDTO {

    public interface OnCreate {}
    public interface OnUpdate {}

    @Min(value = 1, groups = {OnUpdate.class}, message = "El ID debe ser mayor a 0")
    @NotNull(groups = OnUpdate.class)
    @Null(groups = OnCreate.class)
    private Long studentId;

    @Size(min = 2, max = 15, message = "El nombre debe tener entre 2 y 15 caracteres", groups = {OnCreate.class})
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+$", message = "El apellido no debe contener espacios ni números", groups = {OnCreate.class})
    @Pattern(regexp = "^([a-zA-ZáéíóúÁÉÍÓÚñÑ]+)?$", message = "El apellido no debe contener espacios ni números", groups = {OnUpdate.class})
    @NotBlank(groups = {OnCreate.class})
    private String firstName;

    @Size(min = 2, max = 15, message = "El apellido debe tener entre 2 y 15 caracteres", groups = {OnCreate.class})
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+$", message = "El apellido no debe contener espacios ni números", groups = {OnCreate.class})
    @Pattern(regexp = "^([a-zA-ZáéíóúÁÉÍÓÚñÑ]+)?$", message = "El apellido no debe contener espacios ni números", groups = {OnUpdate.class})
    @NotBlank(groups = {OnCreate.class})
    private String lastName;
    
    @Size(min = 5, max = 30, message = "El correo debe tener entre 5 y 50 caracteres", groups = {OnCreate.class})
    @NotBlank(groups = {OnCreate.class})
    @Email(groups = {OnCreate.class, OnUpdate.class})
    @ValidEmailDominio(groups = {OnCreate.class, OnUpdate.class})
    private String email;

    
    private Set<Course> courses = new HashSet<>();


}



