package com.colegio.sam.sistemaspagos.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "grades")
@Data
public class Grade {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String nivel; // "primaria" o "secundaria"

    @Column(nullable = false,precision = 10, scale = 2)
    private Double montoMensualidad;

    @OneToMany(mappedBy = "grade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> estudiantes;
}
