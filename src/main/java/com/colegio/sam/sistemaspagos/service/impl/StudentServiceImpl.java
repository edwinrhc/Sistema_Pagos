package com.colegio.sam.sistemaspagos.service.impl;

import com.colegio.sam.sistemaspagos.entity.Student;
import com.colegio.sam.sistemaspagos.repository.StudentRepository;
import com.colegio.sam.sistemaspagos.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();

    }
}
