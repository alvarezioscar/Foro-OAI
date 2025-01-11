package com.alura.miprimerforo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Un controller de Rest
@RequestMapping("/hello") //Darle la ruta
public class HelloController {

    @GetMapping //Indicarle que para este metodo se aplica un GET
    public String helloWord(){
        return "Estas haciendo una petición GET";
    }
}
