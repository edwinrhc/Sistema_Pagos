package com.colegio.sam.sistemaspagos.service.impl;

import com.colegio.sam.sistemaspagos.dto.StudentsDTO;
import com.colegio.sam.sistemaspagos.entity.Parent;
import com.colegio.sam.sistemaspagos.entity.Student;
import com.colegio.sam.sistemaspagos.entity.User;
import com.colegio.sam.sistemaspagos.repository.ParentRepository;
import com.colegio.sam.sistemaspagos.repository.StudentRepository;
import com.colegio.sam.sistemaspagos.service.IStudentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ParentRepository parentRepository;

    @Override
    @Transactional
    public void guardarStudents(StudentsDTO studentsDTO) throws ParseException {
        try {

            Parent parent = parentRepository.findById(studentsDTO.getParentId()).orElseThrow( () -> new RuntimeException("Padre no encontrado"));


            Student student = new Student();
            student.setTipo_doc(studentsDTO.getTipo_doc());
            student.setNum_doc(studentsDTO.getNum_doc());
            student.setNombre(studentsDTO.getNombre());
            student.setApellido_paterno(studentsDTO.getApellido_paterno());
            student.setApellido_materno(studentsDTO.getApellido_materno());
            student.setParent(parent);
            //  TODO:  Falta el Grade
            student.setCreatedAt(LocalDateTime.now());

            studentRepository.save(student);

        }catch (IllegalArgumentException e){
            throw e;
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error al guardar el estudiante" + e.getMessage(),e);
        }
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();

    }
}
