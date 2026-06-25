package com.example.crudrapido.client;

import com.example.crudrapido.dto.PostExternalDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import java.util.List;


//accept lo que esperamos recibir
//contetype lo que mandamos 
@HttpExchange(accept = "application/json", contentType = "application/json")
public interface JsonPlaceholderClient {

    //mandamos peticion get a la ruta /post
    @GetExchange("/posts")
    List<PostExternalDTO> obtenerTodosLosPosts();
    
    @GetExchange("/posts/{id}")
    PostExternalDTO obtenerPostPorId(@PathVariable("id") Long id);

    @PostExchange("/posts")
    PostExternalDTO crearPost(@RequestBody PostExternalDTO nuevoPost);
}