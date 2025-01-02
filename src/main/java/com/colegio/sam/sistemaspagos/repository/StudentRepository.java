package com.colegio.sam.sistemaspagos.repository;

import com.colegio.sam.sistemaspagos.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
