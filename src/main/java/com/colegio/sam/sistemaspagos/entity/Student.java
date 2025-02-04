package com.colegio.sam.sistemaspagos.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "students")
@Data
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

    private Date createdAt;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;

    public Long getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Long idStudent) {
        this.idStudent = idStudent;
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

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
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


}
