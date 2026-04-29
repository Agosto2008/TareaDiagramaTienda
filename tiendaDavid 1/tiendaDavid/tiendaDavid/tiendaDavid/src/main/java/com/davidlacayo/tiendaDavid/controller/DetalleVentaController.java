package com.davidlacayo.tiendaDavid.controller;


import com.davidlacayo.tiendaDavid.entity.DetalleVenta;
import com.davidlacayo.tiendaDavid.service.DetalleVentaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Validated
@Controller
@RequestMapping("/detalle")
public class DetalleVentaController {
    private DetalleVentaService  detalleVentaService;

    public DetalleVentaController(DetalleVentaService detalleVentaService) {
        this.detalleVentaService = detalleVentaService;
    }


    @GetMapping("/lista")
    public String listarDetalleVenta(Model model){
        model.addAttribute("detalleVenta", detalleVentaService.listarDetalleVentas());
        return "detalleVenta";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoDetalleVenta(Model model) {
        model.addAttribute("detalleVenta", new DetalleVenta());
        model.addAttribute("modoEdicion", false);
        return "detalleVenta-form";
    }

    @PostMapping("/guardar")
    public String crearDetalleVenta(@Valid @RequestBody DetalleVenta detalleVenta, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", false);
            return "detalleVenta-form";
        }
        detalleVentaService.crearDetalleVenta(detalleVenta);
        return "redirect:/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarDetalleVenta(@PathVariable Integer id, Model model) {
        DetalleVenta detalleVenta = detalleVentaService.buscarPorIdDetalleVenta(id);

        model.addAttribute("detalleVenta", detalleVenta);
        model.addAttribute("modoEdicion", true);

        return "detalleVenta-form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarDetalleVenta(@PathVariable
                                  @Min(value = 1, message = "El id debe ser igual o mayor a 1")
                                  Integer id) {
        detalleVentaService.eliminarDetalleVenta(id);
        return "redirect:/detalleVenta";
    }

}
