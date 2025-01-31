package com.colegio.sam.sistemaspagos.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "students")
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStudent;

    @Column(nullable = false)
    private Integer tipo_doc;

    @Column(nullable = false)
    private String num_doc;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido_paterno;

    @Column(nullable = false)
    private String apellido_materno;

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

    private Date createdAt;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;

}
