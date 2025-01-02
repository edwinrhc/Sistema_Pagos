package com.colegio.sam.sistemaspagos.service.impl;

import com.colegio.sam.sistemaspagos.entity.Parent;
import com.colegio.sam.sistemaspagos.repository.ParentRepository;
import com.colegio.sam.sistemaspagos.service.IParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
