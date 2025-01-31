package com.colegio.sam.sistemaspagos.controller;


import com.colegio.sam.sistemaspagos.entity.Parent;
import com.colegio.sam.sistemaspagos.entity.Student;
import com.colegio.sam.sistemaspagos.service.IParentService;
import com.colegio.sam.sistemaspagos.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IParentService parentService;

    @Autowired
    private IStudentService studentService;


    @GetMapping("/students")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String students(Model model){

        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "admin/students";
    }


    @GetMapping("/parents")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String parents(Model model){
        // Listar padres
        List<Parent> parents = parentService.getAllParents();
        model.addAttribute("parents", parents);
        return "admin/parents";
    }
}
