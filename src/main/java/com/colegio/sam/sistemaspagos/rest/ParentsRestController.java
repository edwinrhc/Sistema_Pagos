package com.colegio.sam.sistemaspagos.rest;

import com.colegio.sam.sistemaspagos.dto.ParentsDTO;
import com.colegio.sam.sistemaspagos.service.IParentService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@SessionAttributes("parents")
@RestController
@RequestMapping("/api/parents")
public class ParentsRestController {

    @Autowired
    private IParentService parentService;


    @GetMapping("/get")
    public ResponseEntity<Map<String,Object>> getForm(){
        ParentsDTO parentsDTO = new ParentsDTO(); // Objeto vacío para el formulario
        // Preparamos el map
        Map<String,Object> response = new HashMap<>();
        response.put("parents", parentsDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Validated @RequestBody ParentsDTO parentsDTO){

        try{
            parentService.guardarParent(parentsDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Registro creado exitosamente.");

        }
       catch (IllegalArgumentException e){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor: " + e.getMessage());
        }

    }




}
