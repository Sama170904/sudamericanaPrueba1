package com.example.validation;

import jakarta.validation.ConstraintValidator; 
import jakarta.validation.ConstraintValidatorContext;

public class EmailDominioValidator implements ConstraintValidator<ValidEmailDominio, String> {
    private static final String REGEX_DOMINIO = "^[A-Za-z0-9+_.-]+@sasf\\.com$";

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || email.isEmpty()) {
            return true; 
        }
        return email.matches(REGEX_DOMINIO);
    }
}
