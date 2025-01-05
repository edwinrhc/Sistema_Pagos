package com.colegio.sam.sistemaspagos.dto;

import com.colegio.sam.sistemaspagos.entity.Student;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.List;




@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentsDTO {


    private Long idParent;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String email;
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
}
