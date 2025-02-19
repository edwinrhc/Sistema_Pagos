package com.colegio.sam.sistemaspagos.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "grades")
@Data
public class Grade {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGrade;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String nivel; // "primaria" o "secundaria"

    @Column(nullable = false,precision = 10, scale = 2)
    private BigDecimal montoMensualidad;

    @OneToMany(
            mappedBy = "grade",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> estudiantes;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}
