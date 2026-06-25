package com.example.crudrapido.entity;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@AllArgsConstructor //no me corria swagger por eso las agregue
@NoArgsConstructor  //no me corria swagger por eso las agregue
@Entity
@SQLDelete(sql = "UPDATE tbl_student SET estado = 'INACTIVO' WHERE student_id = ?") //cuando se elimina se hace un update 
@SQLRestriction("estado = 'ACTIVO' || estado = 'INACTIVO'")  //cunado se haga un select solo se traen los de estado activo
@Table(name = "tbl_student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;


    //Enumerated es para decirle a java que vamos a usar un enum, y el EnumType.STRING 
    //para que lo guarde como texto en la base de datos, si no se pone eso lo guarda como un numero y no es tan legible
    @Enumerated(EnumType.STRING) 
    @Column(nullable = false, columnDefinition = "VARCHAR(20) DEFAULT 'ACTIVO'")
    @Builder.Default
    private Estado estado = Estado.ACTIVO;

    public enum Estado {
        ACTIVO, INACTIVO, N
    }


    @ManyToMany
    @JoinTable(
        name = "tbl_student_course", // Nombre de la tabla intermedia que Spring creará
        joinColumns = @JoinColumn(name = "student_id"), // Columna que apunta a Student
        inverseJoinColumns = @JoinColumn(name = "course_id") // Columna que apunta a Course
    )
    @ToString.Exclude // <--- Bloquea el bucle del toString()
    @EqualsAndHashCode.Exclude // <--- Bloquea problemas de memoria al comparar
    private Set<Course> courses = new HashSet<>();

}
