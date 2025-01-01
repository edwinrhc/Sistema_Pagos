package com.colegio.sam.sistemaspagos.controller;

import com.colegio.sam.sistemaspagos.util.Constantes;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalController {

    @ModelAttribute
    public void addGlobalAttributes(Model model) {
        model.addAttribute("version", Constantes.VERSION);
    }
}
