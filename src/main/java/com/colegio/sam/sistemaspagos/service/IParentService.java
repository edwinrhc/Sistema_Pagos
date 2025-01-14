package com.colegio.sam.sistemaspagos.service;

import com.colegio.sam.sistemaspagos.dto.ParentsDTO;
import com.colegio.sam.sistemaspagos.entity.Parent;

import java.text.ParseException;
import java.util.List;

public interface IParentService {

     List<Parent> getAllParents();

     Parent saveParent(Parent parent);

     Parent findOne(Long id);

     void guardarParent(ParentsDTO parentsDTO) throws ParseException;

     void validarCorreoUnico(String email);
}
