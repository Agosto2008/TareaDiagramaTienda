package com.davidlacayo.tiendaDavid.controller;

import com.davidlacayo.tiendaDavid.entity.Productos;
import com.davidlacayo.tiendaDavid.service.ProductosService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
public class ProductosController {

    private final ProductosService productosService;

    public ProductosController(ProductosService productosService) {
        this.productosService = productosService;
    }

    @GetMapping("")
    public String listarProductos(Model model) {
        model.addAttribute("productos", productosService.listarProductos());
        return "productos";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("productos", new Productos());
        model.addAttribute("modoEdicion", false);
        return "productos-form";
    }

    @PostMapping("/guardar")
    public String crearProductos(@Valid @ModelAttribute("productos") Productos productos,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", false);
            return "productos-form";
        }
        productosService.crearProductos(productos);
        return "redirect:/productos";  // CORREGIDO: antes redirigía a /usuarios
    }

    @GetMapping("/actualizar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        model.addAttribute("productos", productosService.buscarPorIdProductos(id));
        model.addAttribute("modoEdicion", true);
        return "productos-form";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarProductos(@Valid @ModelAttribute("productos") Productos productos,
                                      @PathVariable Integer id,
                                      BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", true);
            return "productos-form";
        }
        productosService.actualizarProductos(id, productos);
        return "redirect:/productos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProductos(@PathVariable Integer id) {
        productosService.eliminarProductos(id);
        return "redirect:/productos";
    }
}