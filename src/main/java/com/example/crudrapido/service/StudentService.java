package com.example.crudrapido.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.crudrapido.dto.StudentDTO;
import com.example.crudrapido.entity.Student;
import com.example.crudrapido.repository.StudentRepository;

import lombok.Builder;


@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    /*
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }
    */

    public Page<Student> getStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    public Student getStudent(Long id) {
        return studentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    }

    public Student create(StudentDTO student) {
        Student estudianteNuevo = Student.builder()
            .firstName(student.getFirstName().toUpperCase())
            .lastName(student.getLastName().toUpperCase())
            .email(student.getEmail())
            .build();
        studentRepository.save(estudianteNuevo);
        return estudianteNuevo;
    }

    public Student update(StudentDTO student) {
        Student estudianteExistente = studentRepository.findById(student.getStudentId())
        .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        if (student.getFirstName() != null && !student.getFirstName().isEmpty()) {
            estudianteExistente.setFirstName(student.getFirstName().toUpperCase());
        }
        if (student.getLastName() != null && !student.getLastName().isEmpty()) {
            estudianteExistente.setLastName(student.getLastName().toUpperCase());
        }
        if (student.getEmail() != null && !student.getEmail().isEmpty()) {
            estudianteExistente.setEmail(student.getEmail());
        }
        studentRepository.save(estudianteExistente);
        return estudianteExistente;
        //se puede usar el save porque como se detecta que es el id es el mismo, 
        // se actualiza el registro en lugar de crear uno nuevo
    }

    public void delete(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("No se encontró el estudiante con ID: " + id);
        }
        studentRepository.deleteById(id);
    }
}
