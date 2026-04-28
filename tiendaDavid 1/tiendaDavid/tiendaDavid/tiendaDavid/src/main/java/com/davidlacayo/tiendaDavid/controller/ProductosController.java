package com.davidlacayo.tiendaDavid.controller;


import com.davidlacayo.tiendaDavid.entity.Productos;
import com.davidlacayo.tiendaDavid.entity.Usuarios;
import com.davidlacayo.tiendaDavid.service.ProductosService;
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
@RequestMapping("/productos")
public class ProductosController {
    private ProductosService productosService;

    public ProductosController(ProductosService productosService) {
        this.productosService = productosService;
    }


    @GetMapping("/lista")
    public String listarProductos(Model model){
        model.addAttribute("productos", productosService.listarProductos());
        return "productos";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoProductos(Model model) {
        model.addAttribute("productos", new Productos());
        model.addAttribute("modoEdicion", false);
        return "productos-form";
    }

    @PostMapping("/guardar")
    public String crearProductos(@Valid @RequestBody Productos productos, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", false);
            return "productos-form";
        }
        productosService.crearProductos(productos);
        return "redirect:/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarProductos(@PathVariable Integer id, Model model) {
        Productos productos = productosService.buscarPorIdProductos(id);

        model.addAttribute("productos", productos);
        model.addAttribute("modoEdicion", true);

        return "productos-form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProductos(@PathVariable
                                  @Min(value = 1, message = "El id debe ser igual o mayor a 1")
                                  Integer id) {
        productosService.eliminarProductos(id);
        return "redirect:/productos";
    }

}
