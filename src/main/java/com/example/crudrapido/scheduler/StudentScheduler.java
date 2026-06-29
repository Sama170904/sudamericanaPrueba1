package com.example.crudrapido.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.crudrapido.service.StudentService;

@Component
public class StudentScheduler {
    
    @Autowired
    private StudentService studentService;

    @Scheduled(fixedRate = 600000)
    public void mensajeAlumnos(){
        System.out.println("mensaje de @scheduled");
    }
}
