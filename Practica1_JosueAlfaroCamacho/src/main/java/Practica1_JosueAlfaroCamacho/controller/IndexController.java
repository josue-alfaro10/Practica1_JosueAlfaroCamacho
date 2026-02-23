/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Practica1_JosueAlfaroCamacho.controller;


import Practica1_JosueAlfaroCamacho.service.ArbolService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {
    
    // Las últimas versiones de Spring, recomiendan utilziar final y contructor en lugar de @autowired
    private final ArbolService arbolService;

    public IndexController(ArbolService arbolService) {
        this.arbolService = arbolService;
    }

    @GetMapping("/")
    public String cargarPaginaInicio(Model model) {
        var lista = arbolService.getArboles();
        model.addAttribute("arboles", lista);
        return "index";
    }
}
