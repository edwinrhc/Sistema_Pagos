package com.colegio.sam.sistemaspagos.service.impl;

import com.colegio.sam.sistemaspagos.dto.ParentsDTO;
import com.colegio.sam.sistemaspagos.entity.Parent;
import com.colegio.sam.sistemaspagos.entity.User;
import com.colegio.sam.sistemaspagos.repository.ParentRepository;
import com.colegio.sam.sistemaspagos.repository.UserRepository;
import com.colegio.sam.sistemaspagos.service.IParentService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public class ParentServiceImpl implements IParentService {


    private static final Logger logger = LoggerFactory.getLogger(ParentServiceImpl.class);

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

            // si el padre cuenta con usuario, usarlo; de lo contrario crear uno nuevo
            User user;
            if(parent.getUser() != null) {
                user = parent.getUser();
            }else{
                user = new User();
                user.setRoles("USER");
            }

            // Asignar valores a User;
            user.setUsername(parentsDTO.getEmail());
            user.setPassword(passwordEncoder.encode(parentsDTO.getNum_doc()));

            user  = userRepository.save(user);

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

            parent.setUser(user);

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
    public void actualizarParent(Long parentiId,ParentsDTO parentsDTO) throws ParseException {
        try {
            // Buscar el registro existente
            Parent existingParent = parentRepository.findById(parentiId)
                    .orElseThrow(() -> new IllegalArgumentException("El padre con ID " + parentiId + " no existe."));

            // Validar si el correo ya está registrado en otro registro
            boolean emailExistsInAnotherRecord = parentRepository.existsByEmailAndIdParentNot(parentsDTO.getEmail(),parentiId);
            if(emailExistsInAnotherRecord) {
                throw new IllegalArgumentException("El correo ya esta registrado");
            }

            // Actualizar los campos del registro existente
            existingParent.setTipo_doc(parentsDTO.getTipo_doc());
            existingParent.setNum_doc(parentsDTO.getNum_doc());
            existingParent.setNombre(parentsDTO.getNombre());
            existingParent.setApellido_paterno(parentsDTO.getApellido_paterno());
            existingParent.setApellido_materno(parentsDTO.getApellido_materno());
            existingParent.setEmail(parentsDTO.getEmail());
            existingParent.setTelefono(parentsDTO.getTelefono());
            existingParent.setEstado(parentsDTO.getEstado());

            // Obtener usuario asociado
            User user = existingParent.getUser();
            if(user != null){
                // Actualizar el username del usuario en la tabla user
                user.setUsername(parentsDTO.getEmail());
                userRepository.save(user);
            }


            // Registrar el usuario que actualiza el registro
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {
                String username = authentication.getName();
                existingParent.setUpdatedBy(username); // Asume que tienes un campo para registrar el usuario que actualiza
                existingParent.setUpdatedAt(new Date());
            } else {
                throw new RuntimeException("Error al obtener el usuario autenticado");
            }

            // Guardar los cambios
            parentRepository.save(existingParent);

        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar el registro de Padres" + e.getMessage(),e);
        }
    }

    @Override
    public void validarCorreoUnico(String email) {
        if (parentRepository.existsByEmail(email)) {
            throw new RuntimeException("El correo ya esta registrado");
        }
    }


}
