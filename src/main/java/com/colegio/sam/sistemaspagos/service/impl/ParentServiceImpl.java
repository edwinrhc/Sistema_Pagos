package com.colegio.sam.sistemaspagos.service.impl;

import com.colegio.sam.sistemaspagos.dto.ParentsDTO;
import com.colegio.sam.sistemaspagos.entity.Parent;
import com.colegio.sam.sistemaspagos.repository.ParentRepository;
import com.colegio.sam.sistemaspagos.service.IParentService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class ParentServiceImpl implements IParentService {


    private static final Logger logger = LoggerFactory.getLogger(ParentServiceImpl.class);

    @Autowired
    private ParentRepository parentRepository;

    @Override
    public List<Parent> getAllParents() {
        return parentRepository.findAll();
    }

    @Override
    public Parent saveParent(Parent parent) {
        return parentRepository.save(parent);
    }

    @Override
    public Parent findOne(Long id) {
        try {
            return parentRepository.findById(id).orElse(null);
        } catch (Exception e) {
            logger.error("Error al encontrar el padre con ID"+ id ,e );
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public void guardarParent(ParentsDTO parentsDTO) {

        try {

            if (parentRepository.existsByEmail(parentsDTO.getEmail())) {
                throw new IllegalArgumentException("El correo ya esta registrado");
            }

            Parent parent = new Parent();
            parent.setTipo_doc(parentsDTO.getTipo_doc());
            parent.setNum_doc(parentsDTO.getNum_doc());
            parent.setNombre(parentsDTO.getNombre());
            parent.setApellido_paterno(parentsDTO.getApellido_paterno());
            parent.setApellido_materno(parentsDTO.getApellido_materno());
            parent.setEmail(parentsDTO.getEmail());
            parent.setTelefono(parentsDTO.getTelefono());
            parent.setEstado(parentsDTO.getEstado());

            // Obtener el usuario autenticado
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {
                String username = authentication.getName();
                parent.setCreatedBy(username);
            } else {
                throw new RuntimeException("Error al obtener el usuario autenticado");
            }

            parentRepository.save(parent);
        } catch (IllegalArgumentException e) {
            // Lanza la misma excepción para que sea capturada en el controlador
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al guardar el registro de Padres" + e.getMessage(), e);
        }
    }

    @Override
    public void validarCorreoUnico(String email) {
        if (parentRepository.existsByEmail(email)) {
            throw new RuntimeException("El correo ya esta registrado");
        }
    }


}
