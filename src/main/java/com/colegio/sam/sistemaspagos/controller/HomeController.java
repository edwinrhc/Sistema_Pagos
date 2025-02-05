package com.colegio.sam.sistemaspagos.controller;

import com.colegio.sam.sistemaspagos.entity.Parent;
import com.colegio.sam.sistemaspagos.entity.User;
import com.colegio.sam.sistemaspagos.repository.ParentRepository;
import com.colegio.sam.sistemaspagos.repository.UserRepository;
import com.colegio.sam.sistemaspagos.service.IParentService;
import com.colegio.sam.sistemaspagos.service.impl.ParentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private UserRepository userRepository;

/*    @GetMapping("/home")
    public String home(Model model, Principal principal, Authentication authentication){
        String usuarioLogeado = ((UserDetails) authentication.getPrincipal()).getUsername();
        String username = principal.getName();
        model.addAttribute("username",username);
        return "home";
    }*/

    @GetMapping("/home")
    public String hombe(Model model, Principal principal) {
        if (principal != null) { //Verifica si hay un usuario autenticado
            String username = principal.getName(); // Obtiene el username
            User user = userRepository.findByUsername(username);
            if (user != null) {
                Parent parent = parentRepository.findByUser(user);
                if(parent != null){
                    model.addAttribute("nombreUsuario", parent.getNombre());
                }else{
                    model.addAttribute("nombreUsuario",username);//Entonces mostramos el username
                }
            }
        }

        return "home";
    }


    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String admin() {
        System.out.println("Admin endpoint accessed");
        return "admin";
    }


}
