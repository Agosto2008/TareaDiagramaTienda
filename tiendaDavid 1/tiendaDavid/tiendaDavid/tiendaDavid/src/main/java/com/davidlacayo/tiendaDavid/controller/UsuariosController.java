package com.davidlacayo.tiendaDavid.controller;

import com.davidlacayo.tiendaDavid.entity.Usuarios;
import com.davidlacayo.tiendaDavid.service.UsuariosService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
@Validated
@RequestMapping("/usuarios")
public class UsuariosController {

    private final UsuariosService usuarioService;

    public UsuariosController(UsuariosService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("")
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        return "usuarios";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuarios", new Usuarios());
        model.addAttribute("modoEdicion", false);
        return "usuarios-form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("usuarios") Usuarios usuarios,
                          BindingResult result,
                          Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", false);
            return "usuarios-form";
        }
        usuarioService.crearUsuarios(usuarios);
        return "redirect:/usuarios";
    }

    @GetMapping("/actualizar/{id}")
    public String mostrarFormActualizar(@PathVariable Integer id, Model model) {
        Usuarios usuarios = usuarioService.buscarPorIdUsuarios(id);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("modoEdicion", true);
        return "usuarios-form";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@Valid @ModelAttribute("usuarios") Usuarios usuarios,
                             @PathVariable Integer id,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", true);
            return "usuarios-form";
        }
        usuarioService.actualizarUsuarios(id, usuarios);
        return "redirect:/usuarios";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        usuarioService.eliminarUsuarios(id);
        return "redirect:/usuarios";
    }
}