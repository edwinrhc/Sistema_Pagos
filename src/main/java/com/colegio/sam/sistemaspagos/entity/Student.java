package com.colegio.sam.sistemaspagos.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "students")
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @ManyToOne
    @JoinColumn(
            name = "fk_parent_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_STUDENT_PARENT")
    )
    private Parent parent;

    //  Grade
    @ManyToOne
    @JoinColumn(
            name = "FK_grade_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_STUDENT_GRADE"))
    private Grade grade;

}
