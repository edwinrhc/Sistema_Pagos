package com.colegio.sam.sistemaspagos.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(value="error",required = false)String error,
                        @RequestParam(value="logout",required = false)String logout,
                        Model model, Principal principal, RedirectAttributes flash) {



        if(error != null){
            model.addAttribute("error","Error en el login: Nombre de usuario: o Contraseña incorrecta");
        }

        if(logout != null){
            flash.addFlashAttribute("info", "Has cerrado sesion con exito");
            return "redirect:/login"; // Redirige al login después de logout
        }

        return "login";
    }
}
