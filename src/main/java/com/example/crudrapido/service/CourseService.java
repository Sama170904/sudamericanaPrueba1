package com.example.crudrapido.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crudrapido.entity.Course;
import com.example.crudrapido.repository.CourseRepository;
import com.example.crudrapido.repository.StudentRepository;

@Service
public class CourseService {

    @Autowired 
    CourseRepository courseRepository;

    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + courseId));
    }
}
