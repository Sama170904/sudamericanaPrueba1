package com.example.validation;

import jakarta.validation.ConstraintValidator; 
import jakarta.validation.ConstraintValidatorContext;

public class EmailDominioValidator implements ConstraintValidator<ValidEmailDominio, String> {
    public static final String DOMINIO = "sasf.net";
    private static final String REGEX = "^[A-Za-z0-9+_.-]+@" + DOMINIO.replace(".", "\\.") + "$";

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || email.isEmpty()) {
            return true; 
        }
        return email.matches(REGEX);
    }
}


//hacer variable global el "sasf.net"    ya esta
//solucoanr el return del null          ya esta
//probar el filtro                     ya esta
//agregar entidad cursos con endpoint que agregue un estudoiante a un curso y un endpoint que liste los cursos
// con la lista de estudianteque tenga ese curso. 
//en el endpoint de listar estudaintes agregar un filtro que sea opcional, y que sea el nombre del curso


//primera mitad trabajare con spring
//segunda mitad sql server
//React y Javascript

//tener un script con insercciones para la base de datos listo