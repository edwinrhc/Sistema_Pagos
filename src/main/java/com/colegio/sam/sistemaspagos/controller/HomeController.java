package com.colegio.sam.sistemaspagos.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/home")
    public String home(Model model, Principal principal, Authentication authentication){
        String usuarioLogeado = ((UserDetails) authentication.getPrincipal()).getUsername();
        String username = principal.getName();
        model.addAttribute("username",username);
        return "home";
    }


    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String admin(){
        System.out.println("Admin endpoint accessed");
        return "Admin";
    }
}
