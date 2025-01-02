package com.colegio.sam.sistemaspagos.repository;

import com.colegio.sam.sistemaspagos.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {
}
