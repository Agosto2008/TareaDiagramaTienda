package com.davidlacayo.tiendaDavid.controller;


import com.davidlacayo.tiendaDavid.entity.Clientes;
import com.davidlacayo.tiendaDavid.entity.Usuarios;
import com.davidlacayo.tiendaDavid.service.ClientesService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@Controller
@RequestMapping("/clientes")
public class ClientesController {
    private ClientesService clientesService;

    public ClientesController(ClientesService clientesService) {
        this.clientesService = clientesService;
    }


    @GetMapping("/lista")
    public String listarClientes(Model model){
        model.addAttribute("clientes", clientesService.listarClientes());
        return "clientes";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoClientes(Model model) {
        model.addAttribute("clientes", new Clientes());
        model.addAttribute("modoEdicion", false);
        return "clientes-form";
    }

    @PostMapping("/guardar")
    public String crearClientes(@Valid @RequestBody Clientes clientes, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", false);
            return "clientes-form";
        }
        clientesService.crearClientes(clientes);
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarClientes(@PathVariable Integer id, Model model) {
        Clientes clientes = clientesService.buscarPorIdClientes(id);

        model.addAttribute("clientes", clientes);
        model.addAttribute("modoEdicion", true);

        return "clientes-form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarClientes(@PathVariable
                                  @Min(value = 1, message = "El id debe ser igual o mayor a 1")
                                  Integer id) {
        clientesService.eliminarClientes(id);
        return "redirect:/clientes";
    }

}
