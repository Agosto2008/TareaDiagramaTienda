package com.davidlacayo.tiendaDavid.controller;

import com.davidlacayo.tiendaDavid.entity.Usuarios;
import com.davidlacayo.tiendaDavid.service.UsuariosService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosService usuarioService;

    @GetMapping("/lista")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        return "usuarios";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoUsuario(Model model) {
        model.addAttribute("usuarios", new Usuarios());
        model.addAttribute("modoEdicion", false);
        return "usuarios-form";
    }

    @PostMapping("/guardar")
    public String crearUsuarios(@Valid @RequestBody Usuarios usuarios, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", false);
            return "usuarios-form";
        }
        usuarioService.crearUsuarios(usuarios);
        return "redirect:/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarUsuario(@PathVariable Integer id, Model model) {
        Usuarios usuarios = usuarioService.buscarPorIdUsuarios(id);

        model.addAttribute("usuarios", usuarios);
        model.addAttribute("modoEdicion", true);

        return "usuarios-form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable
                                     @Min(value = 1, message = "El id debe ser igual o mayor a 1")
                                     Integer id) {
        usuarioService.eliminarUsuarios(id);
        return "redirect:/usuarios";
    }

}