package com.colegio.sam.sistemaspagos.service.impl;

import com.colegio.sam.sistemaspagos.dto.ParentsDTO;
import com.colegio.sam.sistemaspagos.entity.Parent;
import com.colegio.sam.sistemaspagos.repository.ParentRepository;
import com.colegio.sam.sistemaspagos.service.IParentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class ParentServiceImpl implements IParentService {

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
    @Transactional
    public void guardarParent(ParentsDTO parentsDTO)  {

        try {
            Parent parent = new Parent();
            parent.setNombre(parentsDTO.getNombre());
//            parent.setApellido_paterno(parentsDTO.getApellido_paterno());
//            parent.setApellido_materno(parentsDTO.getApellido_materno());
//            parent.setEmail(parentsDTO.getEmail());
//            parent.setTelefono(parentsDTO.getTelefono());

            // Obtener el usuario autenticado
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication != null && authentication.isAuthenticated()) {
                String username = authentication.getName();
                parent.setCreatedBy(username);
            }else{
                throw new RuntimeException("Error al obtener el usuario autenticado");
            }

            parentRepository.save(parent);
        } catch (Exception e) {
            e.printStackTrace();
                throw new RuntimeException("Error al guardar el registro de Padres" + e.getMessage());
        }
    }


}
