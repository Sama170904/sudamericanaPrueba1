package com.example.crudrapido.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.crudrapido.dto.StudentDTO;
import com.example.crudrapido.entity.Student;
import com.example.crudrapido.repository.StudentRepository;
import com.example.crudrapido.service.StudentService;

import jakarta.annotation.Generated;
import jakarta.validation.constraints.Min;

@Validated
@RestController
@RequestMapping(path = "/api/v1/students")
public class StudentController {
    
    @Autowired
    private StudentService studentService;

    /* 
    @GetMapping
    public List<Student> getAll() {
        return studentService.getStudents();
    }
    */

    @GetMapping
    public Page<Student> getAll(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return studentService.getStudents(pageable);
    }


    @PostMapping
    public Student create(@RequestBody @Validated(StudentDTO.OnCreate.class) StudentDTO student) {
        return studentService.create(student);
    }

    @PutMapping
    public Student update(@RequestBody @Validated(StudentDTO.OnUpdate.class) StudentDTO student) {
        return studentService.update(student);
    }

    @DeleteMapping("/{studentId}")
    public void delete(@PathVariable("studentId") @Min(value = 1, message = "El ID debe ser mayor a 0") Long studentId) {
        studentService.delete(studentId); 
    }  


    @GetMapping("/{studentId}")
    public Student getById(@PathVariable("studentId") Long studentId) {
        return studentService.getStudent(studentId); }
}
