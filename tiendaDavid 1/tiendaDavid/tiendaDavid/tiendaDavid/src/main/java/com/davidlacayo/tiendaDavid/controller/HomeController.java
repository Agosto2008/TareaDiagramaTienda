package com.davidlacayo.tiendaDavid.controller;

import com.davidlacayo.tiendaDavid.entity.Usuarios;
import com.davidlacayo.tiendaDavid.service.AuthService;
import com.davidlacayo.tiendaDavid.service.UsuariosService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class HomeController {

    private final UsuariosService usuarioService;
    private final AuthService authService;

    public HomeController(AuthService authService, UsuariosService usuarioService) {
        this.usuarioService = usuarioService;
        this.authService = authService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "home";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("usuarios", new Usuarios());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid Usuarios usuarios, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        boolean registrado = usuarioService.register(usuarios);

        if (!registrado) {
            model.addAttribute("Error", "El usuario ya existe");
            return "register";
        }
        return "redirect:/login";
    }


    @GetMapping("/")
    public String redirectToHome(){
        return "redirect:/home";
    }


}