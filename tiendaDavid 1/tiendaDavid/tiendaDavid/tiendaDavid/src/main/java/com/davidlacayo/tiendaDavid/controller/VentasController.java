package com.davidlacayo.tiendaDavid.controller;

import com.davidlacayo.tiendaDavid.entity.Usuarios;
import com.davidlacayo.tiendaDavid.entity.Ventas;
import com.davidlacayo.tiendaDavid.service.VentasService;
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
@RequestMapping("/ventas")
public class VentasController {
    private VentasService ventasService;
    public VentasController(VentasService ventasService) {
        this.ventasService = ventasService;
    }


    @GetMapping("/lista")
    public String listarVentas(Model model){
        model.addAttribute("ventas", ventasService.listarVentas());
        return "ventas";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoVentas(Model model) {
        model.addAttribute("ventas", new Ventas());
        model.addAttribute("modoEdicion", false);
        return "ventas-form";
    }

    @PostMapping("/guardar")
    public String crearVentas(@Valid @RequestBody Ventas ventas, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", false);
            return "ventas-form";
        }
        ventasService.crearVentas(ventas);
        return "redirect:/ventas";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarVentas(@PathVariable Integer id, Model model) {
        Ventas ventas = ventasService.buscarPorIdVentas(id);

        model.addAttribute("ventas", ventas);
        model.addAttribute("modoEdicion", true);

        return "ventas-form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarVentas(@PathVariable
                                  @Min(value = 1, message = "El id debe ser igual o mayor a 1")
                                  Integer id) {
        ventasService.eliminarVentas(id);
        return "redirect:/ventas";
    }


}
