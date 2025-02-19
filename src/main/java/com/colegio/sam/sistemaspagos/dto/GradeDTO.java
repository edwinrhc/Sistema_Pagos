package com.colegio.sam.sistemaspagos.dto;

import com.colegio.sam.sistemaspagos.entity.Student;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeDTO {


    private Long idGrade;

    @NotBlank(message = "El nombre del grado es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "El nivel es obligatorio")
    @Pattern(regexp = "^(primaria|secundaria)$", message = "El nivel debe ser 'primaria' o 'secundaria'")
    private String nivel;

    @NotNull(message = "El monto de la mensualidad es obligatorio")
    @DecimalMin(value = "0.00", inclusive = false, message = "El monto de la mensualidad debe ser mayor a 0")
    @Digits(integer = 6, fraction = 2, message = "El monto debe tener máximo 6 enteros y 2 decimales")
    private BigDecimal montoMensualidad;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

}
