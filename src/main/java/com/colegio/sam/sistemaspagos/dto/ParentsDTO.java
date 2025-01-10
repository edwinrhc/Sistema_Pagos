package com.colegio.sam.sistemaspagos.dto;

import com.colegio.sam.sistemaspagos.entity.Student;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Date;
import java.util.List;




@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentsDTO {


    private Long idParent;


    @NotBlank(message = "El tipo de documento es obligatorio")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s\\.]+$", message = "El tipo de documento solo puede contener letras, espacios y el punto (.)")
    private String tipo_doc;

    @NotBlank(message = "El número de documento es obligatorio")
    @Pattern(regexp = "^(\\d{7,15}|[A-Za-z0-9\\.\\-]{7,15})$",
            message = "El número de documento debe tener entre 7 y 15 caracteres alfanuméricos. Para documentos nacionales, solo se permiten números.")
    private String num_doc;


    @NotBlank(message = "El nombre es obligatorio")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El nombre solo puede contener letras y espacios")
    private String nombre;


    @NotBlank(message = "El apellido paterno es obligatorio")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El apellido paterno solo puede contener letras y espacios")
    private String apellido_paterno;

    @NotBlank(message = "El apellido materno es obligatorio")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El apellido materno solo puede contener letras y espacios")
    private String apellido_materno;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo no tiene un formato válido")
    private String email;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^(?!.*(\\d)\\1{2,})([0-9]{7,15})$", message = "El teléfono debe ser numérico y no contener números consecutivos")
    private String telefono;


    private Date createdAt;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;

//    @JsonProperty("hijos")
    private List<Student> hijos;

    public Long getIdParent() {
        return idParent;
    }

    public void setIdParent(Long idParent) {
        this.idParent = idParent;
    }

    public String getTipo_doc() {
        return tipo_doc;
    }

    public void setTipo_doc(String tipo_doc) {
        this.tipo_doc = tipo_doc;
    }

    public String getNum_doc() {
        return num_doc;
    }

    public void setNum_doc(String num_doc) {
        this.num_doc = num_doc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public List<Student> getHijos() {
        return hijos;
    }

    public void setHijos(List<Student> hijos) {
        this.hijos = hijos;
    }
}
