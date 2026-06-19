package com.example.validation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailDominioValidator.class)
public @interface ValidEmailDominio {
    String message() default "El correo debe pertenecer al dominio "+EmailDominioValidator.DOMINIO;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

//la validacion de que el mensaje se dispare cuando me regresa un false la logica de esta etiqueta
//esta en el motor de validacion de spring(hibernate valdiator)