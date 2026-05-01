package com.davidlacayo.tiendaDavid.controller;

import com.davidlacayo.tiendaDavid.entity.Clientes;
import com.davidlacayo.tiendaDavid.service.ClientesService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
public class ClientesController {

    private final ClientesService clientesService;

    public ClientesController(ClientesService clientesService) {
        this.clientesService = clientesService;
    }

    @GetMapping("")
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clientesService.listarClientes());
        return "clientes";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("clientes", new Clientes());
        model.addAttribute("modoEdicion", false);
        return "clientes-form";
    }

    @PostMapping("/guardar")
    public String crearClientes(@Valid @ModelAttribute("clientes") Clientes clientes,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", false);
            return "clientes-form";
        }
        clientesService.crearClientes(clientes);
        return "redirect:/clientes";
    }

    @GetMapping("/actualizar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        model.addAttribute("clientes", clientesService.buscarPorIdClientes(id));
        model.addAttribute("modoEdicion", true);
        return "clientes-form";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarClientes(@Valid @ModelAttribute("clientes") Clientes clientes,
                                     @PathVariable Integer id,
                                     BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", true);
            return "clientes-form";
        }
        clientesService.actualizarClientes(id, clientes);
        return "redirect:/clientes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarClientes(@PathVariable Integer id) {
        clientesService.eliminarClientes(id);
        return "redirect:/clientes";
    }
}