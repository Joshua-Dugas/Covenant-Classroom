package com.dugas.covenantclassroom.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/CovenantClassroom")
public class HomeController {
    @GetMapping(path = {"", "/"})
    public String index() {
        return "index";
    }
}
