package com.davidlacayo.tiendaDavid.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Error de llave foránea o duplicado en BD
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDataIntegrity(DataIntegrityViolationException ex, Model model) {
        String mensaje = "Error de integridad de datos. ";

        String causa = ex.getMostSpecificCause().getMessage().toLowerCase();

        if (causa.contains("foreign key")) {
            mensaje += "El código que ingresaste no existe en el sistema. Verifica que el cliente, usuario, producto o venta exista antes de continuar.";
        } else if (causa.contains("duplicate") || causa.contains("unique")) {
            mensaje += "Ya existe un registro con ese ID o código. Usa uno diferente.";
        } else {
            mensaje += "Verifica que todos los datos ingresados sean correctos.";
        }

        model.addAttribute("errorMensaje", mensaje);
        return "error";
    }

    // Recurso no encontrado
    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleNotFound(ResourceNotFoundException ex, Model model) {
        model.addAttribute("errorMensaje", ex.getMessage());
        return "error";
    }

    // Argumento inválido
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgument(IllegalArgumentException ex, Model model) {
        model.addAttribute("errorMensaje", ex.getMessage());
        return "error";
    }

    // Cualquier otro error no controlado
    @ExceptionHandler(Exception.class)
    public String handleGeneral(Exception ex, Model model) {
        model.addAttribute("errorMensaje", "Ocurrió un error inesperado: " + ex.getMessage());
        return "error";
    }
}