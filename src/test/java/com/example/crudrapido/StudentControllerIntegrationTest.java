package com.example.crudrapido;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.*;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.crudrapido.service.StudentService;

import org.springframework.http.MediaType;

// Importaciones estáticas cruciales para escribir las peticiones y verificaciones de forma limpia
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest //le dice a spring, antes de ejecutar estas pruebas levanta toda la aplicacion en segundo plano
@AutoConfigureMockMvc //le dice a spring que levate a mockmvc que es el motor que simula las peticiones http sin levantar un servidor en realidad
public class StudentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    //@MockitoBean
    //private StudentService studentService;

    @Test //le dice a spring que lo de abajo es un caso de prueba 
    void deberiaListarTodosLosEstudiantes() throws Exception {
        // Simula el comportamiento del endpoint GET /api/v1/students
        mockMvc.perform(get("/api/v1/students")
                .contentType(MediaType.APPLICATION_JSON))
                // Verifica que la respuesta del servidor simulado sea 200 OK
                .andExpect(status().isOk())
                // Verifica que el tipo de contenido devuelto sea JSON
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void deberiaCrearEstudianteExitosamente() throws Exception {
        // Objeto JSON basado en tus reglas de validación (Nombre/Apellido entre 2 y 15 caracteres)
        String estudianteJson = """
                {
                    "firstName": "Estefanooo",
                    "lastName": "Samaniego",
                    "email": "estefano@sasf.net"
                }
                """;

        // Simula la petición POST para crear un estudiante
        mockMvc.perform(post("/api/v1/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(estudianteJson))
                // Valida que el código de estado devuelto sea 200 OK (o 201 Created si así lo configuraste)
                .andExpect(status().isOk());
    }

    @Test
    void deberiaFallarAlCrearEstudianteConNombreMuyCorto() throws Exception {
        // JSON inválido: "E" tiene solo 1 caracter (rompe la regla de validación de 2 a 15 caracteres)
        String estudianteInvalidoJson = """
                {
                    "firstName": "E",
                    "lastName": "Cabrera",
                    "email": "error@ejemplo.com"
                }
                """;

        mockMvc.perform(post("/api/v1/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(estudianteInvalidoJson))
                // Al fallar la validación de Spring Validation, el GlobalExceptionHandler debería capturarlo y devolver 400 Bad Request
                .andExpect(status().isBadRequest());
    }
}