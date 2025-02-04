package com.colegio.sam.sistemaspagos.dto;

import com.colegio.sam.sistemaspagos.entity.Student;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Date;
import java.util.List;




@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentsDTO {


    private Long idParent;

    @NotNull
    private Integer tipo_doc;

    @NotBlank(message = "El número de documento es obligatorio")
    @Pattern(regexp = "^[A-Za-z0-9]{3,15}$",
            message = "El número de documento debe tener entre 3 y 15 caracteres alfanuméricos, sin caracteres especiales.")
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


    private Integer estado = 1;


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

    public Integer getTipo_doc() {
        return tipo_doc;
    }

    public void setTipo_doc(Integer tipo_doc) {
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

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

}
