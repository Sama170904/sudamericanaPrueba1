package com.example.crudrapido.repository;

import com.example.crudrapido.entity.Parametros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametroRepository extends JpaRepository<Parametros, String> {
    // Usamos String porque 'clave' es nuestra llave primaria
}