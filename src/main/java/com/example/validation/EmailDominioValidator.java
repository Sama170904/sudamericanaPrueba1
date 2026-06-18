package com.example.validation;

import jakarta.validation.ConstraintValidator; 
import jakarta.validation.ConstraintValidatorContext;

public class EmailDominioValidator implements ConstraintValidator<ValidEmailDominio, String> {
    private static final String REGEX_DOMINIO = "^[A-Za-z0-9+_.-]+@sasf\\.net$";

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || email.isEmpty()) {
            return true; 
        }
        return email.matches(REGEX_DOMINIO);
    }
}


//hacer variable global el "sasf.net"   
//solucoanr el return del null  
//validar el filtro  
//agregar entidad cursos con endpoint que agregue un estudoiante a un curso y un endpoint que liste los cursos
// con la lista de estudianteque tenga ese curso. 
//en el endpoint de listar estudaintes agregar un filtro que sea opcional, y que sea el nombre del curso
