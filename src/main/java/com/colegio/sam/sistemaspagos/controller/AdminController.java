package com.colegio.sam.sistemaspagos.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/students")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String students(){

        return "admin/students";
    }
}
