package com.colegio.sam.sistemaspagos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;


}
