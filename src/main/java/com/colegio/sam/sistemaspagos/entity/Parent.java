package com.colegio.sam.sistemaspagos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

//@AllArgsConstructor
//@NoArgsConstructor
//@Data
@Entity
@Table(
        name="parents",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email",name="UNIQUE_PARENT_EMAIL")
        }
)
public class Parent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idParent;

    @Column(nullable = false)
    private String tipo_doc;

    @Column(nullable = false)
    private String num_doc;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido_paterno;

    @Column(nullable = false)
    private String apellido_materno;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String telefono;


    private Date createdAt;

    @CreatedBy
    private String createdBy;
    private Date updatedAt;

    @LastModifiedBy
    private String updatedBy;


    // Student
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Student> hijos;

    @PrePersist
    public void prePersist() {
        createdAt = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = new Date();
    }


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

    public Parent() {
    }

    public Parent(Long idParent, String tipo_doc, String num_doc, String nombre, String apellido_paterno, String apellido_materno, String email, String telefono, Date createdAt, String createdBy, Date updatedAt, String updatedBy, List<Student> hijos) {
        this.idParent = idParent;
        this.tipo_doc = tipo_doc;
        this.num_doc = num_doc;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.email = email;
        this.telefono = telefono;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.hijos = hijos;
    }
}
