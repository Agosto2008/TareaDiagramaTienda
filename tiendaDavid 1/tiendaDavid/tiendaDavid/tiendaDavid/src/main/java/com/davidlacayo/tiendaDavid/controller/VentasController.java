package com.davidlacayo.tiendaDavid.controller;

import com.davidlacayo.tiendaDavid.entity.Ventas;
import com.davidlacayo.tiendaDavid.service.VentasService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ventas")
public class VentasController {

    private final VentasService ventasService;

    public VentasController(VentasService ventasService) {
        this.ventasService = ventasService;
    }

    @GetMapping("")
    public String listarVentas(Model model) {
        model.addAttribute("ventas", ventasService.listarVentas());
        return "ventas";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("ventas", new Ventas());
        model.addAttribute("modoEdicion", false);
        return "ventas-form";
    }

    @PostMapping("/guardar")
    public String crearVentas(@Valid @ModelAttribute("ventas") Ventas ventas,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", false);
            return "ventas-form";
        }
        ventasService.crearVentas(ventas);
        return "redirect:/ventas";
    }

    @GetMapping("/actualizar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        model.addAttribute("ventas", ventasService.buscarPorIdVentas(id));
        model.addAttribute("modoEdicion", true);
        return "ventas-form";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarVentas(@Valid @ModelAttribute("ventas") Ventas ventas,
                                   @PathVariable Integer id,
                                   BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", true);
            return "ventas-form";
        }
        ventasService.actualizarVentas(id, ventas);
        return "redirect:/ventas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarVentas(@PathVariable Integer id) {
        ventasService.eliminarVentas(id);
        return "redirect:/ventas";
    }
}