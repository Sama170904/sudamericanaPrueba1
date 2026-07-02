package com.example.crudrapido;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestPassword {
    
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        String rawPassword = "admin";
        String hashFromDatabase = "$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.";
        
        // 1. Comprobamos si coinciden
        boolean isMatch = encoder.matches(rawPassword, hashFromDatabase);
        System.out.println("¿La contraseña 'admin' coincide con el hash de la BD?: " + isMatch);
        
        // 2. Generamos un hash totalmente nuevo y fresco por si acaso
        String newHash = encoder.encode(rawPassword);
        System.out.println("Si dio false, copia este nuevo hash en tu base de datos: " + newHash);
    }
}