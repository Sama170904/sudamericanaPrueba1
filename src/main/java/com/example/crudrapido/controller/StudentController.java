package com.example.crudrapido.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crudrapido.dto.StudentDTO;
import com.example.crudrapido.entity.Student;
import com.example.crudrapido.repository.StudentRepository;
import com.example.crudrapido.service.StudentService;

import jakarta.annotation.Generated;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping(path = "/api/v1/students")
public class StudentController {
    
    @Autowired
    private StudentService studentService;
    
    @GetMapping
    public List<Student> getAll() {
        return studentService.getStudents();
    }

    @PostMapping
    public void create(@RequestBody @Validated(StudentDTO.OnCreate.class) StudentDTO student) {
        studentService.create(student);
    }

    @PutMapping
    public void update(@RequestBody @Validated(StudentDTO.OnUpdate.class) StudentDTO student) {
        studentService.update(student);
    }

    @DeleteMapping("/{studentId}")
    public void delete(@PathVariable("studentId") @Min(value = 1, message = "El ID debe ser mayor a 0") Long studentId) {
        if (!studentService.getStudent(studentId).isPresent()) {
            throw new RuntimeException("No se encontró el estudiante con ID: " + studentId);
        }
        studentService.delete(studentId);
    }  

    @GetMapping("/{studentId}")
    public Optional<Student> getById(@PathVariable("studentId") Long studentId) {
        return studentService.getStudent(studentId);
    }




}
