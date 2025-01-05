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
    @JoinColumn(
            name="fk_student_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_PAYMENT_STUDENT"))
    private Student estudiante;

    @ManyToOne
    @JoinColumn(
            name = "fk_parent_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_PAYMENT_PARENT")
    )
    private Parent parent;

}
