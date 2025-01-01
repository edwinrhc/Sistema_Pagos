package com.colegio.sam.sistemaspagos.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name="parents")
@Data
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false, unique = true)
    private String email;

    private String telefono;

    // Student
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Student> hijos;
}
