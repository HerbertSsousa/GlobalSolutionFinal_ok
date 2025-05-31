package org.example.GlobalSolutionFinal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/home"})
    public String home() {
        return "home/home";  // resources/templates/home/home.html (login)
    }
}
