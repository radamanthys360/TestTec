package com.prueba.tec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioRestController {
    
    @GetMapping("/")
    public String main(Model model) {
        return "inicio";
    }

}
