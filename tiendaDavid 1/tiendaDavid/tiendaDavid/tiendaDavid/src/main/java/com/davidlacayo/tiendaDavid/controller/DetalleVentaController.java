package com.davidlacayo.tiendaDavid.controller;

import com.davidlacayo.tiendaDavid.entity.DetalleVenta;
import com.davidlacayo.tiendaDavid.service.DetalleVentaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/detalle")
public class DetalleVentaController {

    private final DetalleVentaService detalleVentaService;

    public DetalleVentaController(DetalleVentaService detalleVentaService) {
        this.detalleVentaService = detalleVentaService;
    }

    @GetMapping("")
    public String listarDetalleVenta(Model model) {
        model.addAttribute("detalleVenta", detalleVentaService.listarDetalleVentas());
        return "detalleVenta";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("detalleVenta", new DetalleVenta());
        model.addAttribute("modoEdicion", false);
        return "detalleVenta-form";
    }

    @PostMapping("/guardar")
    public String crearDetalleVenta(@Valid @ModelAttribute("detalleVenta") DetalleVenta detalleVenta,
                                    BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", false);
            return "detalleVenta-form";
        }
        detalleVentaService.crearDetalleVenta(detalleVenta);
        return "redirect:/detalle";  // CORREGIDO: antes redirigía a /usuarios
    }

    @GetMapping("/actualizar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        model.addAttribute("detalleVenta", detalleVentaService.buscarPorIdDetalleVenta(id));
        model.addAttribute("modoEdicion", true);
        return "detalleVenta-form";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarDetalleVenta(@Valid @ModelAttribute("detalleVenta") DetalleVenta detalleVenta,
                                         @PathVariable Integer id,
                                         BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", true);
            return "detalleVenta-form";
        }
        detalleVentaService.actualizarDetalleVenta(id, detalleVenta);
        return "redirect:/detalle";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarDetalleVenta(@PathVariable Integer id) {
        detalleVentaService.eliminarDetalleVenta(id);
        return "redirect:/detalle";
    }
}