package com.colegio.sam.sistemaspagos.repository;

import com.colegio.sam.sistemaspagos.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ParentRepository extends JpaRepository<Parent, Long> {

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdParentNot(String email, Long idParent);

}
