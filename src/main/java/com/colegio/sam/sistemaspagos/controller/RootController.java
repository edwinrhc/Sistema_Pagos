package com.colegio.sam.sistemaspagos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String redirectToHome(){
        return "redirect:/home";
    }
}
