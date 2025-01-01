package com.colegio.sam.sistemaspagos.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double monto;
    private String metodoPago;
    private String estado;
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name="student_id", nullable = false)
    private Student estudiante;

    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = false)
    private Parent parent;

}
