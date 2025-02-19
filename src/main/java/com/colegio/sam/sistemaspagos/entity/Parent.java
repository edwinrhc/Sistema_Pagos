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

@AllArgsConstructor
@Data
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
    private Integer tipo_doc;

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

    @Column(nullable = false)
    private Integer estado = 1;


    private Date createdAt;

    @CreatedBy
    private String createdBy;
    private Date updatedAt;

    @LastModifiedBy
    private String updatedBy;


    // Student
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Student> hijos;

    @OneToOne
    @JoinColumn(name ="user_id", unique = true)
    private User user;


    @PrePersist
    public void prePersist() {
        createdAt = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = new Date();
    }



    public Parent() {
    this.estado = 1;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "Parent{" +
                "idParent=" + idParent +
                ", tipo_doc=" + tipo_doc +
                ", num_doc='" + num_doc + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido_paterno='" + apellido_paterno + '\'' +
                ", apellido_materno='" + apellido_materno + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", estado=" + estado +
                ", createdAt=" + createdAt +
                ", createdBy='" + createdBy + '\'' +
                ", updatedAt=" + updatedAt +
                ", updatedBy='" + updatedBy + '\'' +
                ", hijos=" + hijos +
                ", user=" + user +
                '}';
    }

    public String getTipoDocDescrption(){
        return switch (tipo_doc){
            case 1 -> "DNI";
            case 2 -> "C.EXT";
            default -> "DNI";
        };
    }
}
