package com.colegio.sam.sistemaspagos.service;

import com.colegio.sam.sistemaspagos.entity.Parent;

import java.util.List;

public interface IParentService {

    List<Parent> getAllParents();

     Parent saveParent(Parent parent);
}
