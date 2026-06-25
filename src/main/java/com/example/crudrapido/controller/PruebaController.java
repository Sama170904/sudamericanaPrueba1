package com.example.crudrapido.controller;

import com.example.crudrapido.client.JsonPlaceholderClient;
import com.example.crudrapido.dto.PostExternalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/prueba")
public class PruebaController {

    // ¡Aquí le pedimos a Spring que nos inyecte el cliente que fabricamos!
    @Autowired
    private JsonPlaceholderClient clienteAPI;

    // Creamos una ruta en nuestro propio servidor para probar el GET
    @GetMapping("/posts")
    public List<PostExternalDTO> probarObtenerPosts() {
        // Llamamos a la interfaz. ¡Spring hace el viaje a internet por nosotros!
        return clienteAPI.obtenerTodosLosPosts();
    }
}