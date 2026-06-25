package com.example.crudrapido.config;

import com.example.crudrapido.client.JsonPlaceholderClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {

    // 1. Configuramos el motor base apuntando a la URL de la API externa
    @Bean
    public WebClient jsonPlaceholderWebClient() {
        return WebClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();
    }

    // 2. Le enseñamos a Spring cómo construir el cliente usando el adaptador moderno
    @Bean
    public JsonPlaceholderClient jsonPlaceholderClient(WebClient jsonPlaceholderWebClient) {
        
        // CORRECCIÓN 1: Usar 'create' en lugar de 'forClient' para compatibilidad con Spring Boot 3.2+
        WebClientAdapter adapter = WebClientAdapter.create(jsonPlaceholderWebClient);
        
        // CORRECCIÓN 2: Usar 'builderFor' pasando el adapter corregido
        HttpServiceProxyFactory httpServiceProxyFactory =
                HttpServiceProxyFactory.builderFor(adapter).build();
        
        return httpServiceProxyFactory.createClient(JsonPlaceholderClient.class);
    }
}