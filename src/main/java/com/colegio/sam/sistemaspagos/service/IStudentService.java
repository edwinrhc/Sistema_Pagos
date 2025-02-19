package com.colegio.sam.sistemaspagos.service;

import com.colegio.sam.sistemaspagos.dto.StudentsDTO;
import com.colegio.sam.sistemaspagos.entity.Student;

import java.text.ParseException;
import java.util.List;

public interface IStudentService {

    void guardarStudents(StudentsDTO studentsDTO) throws ParseException;

    List<Student> getAllStudents();
}
