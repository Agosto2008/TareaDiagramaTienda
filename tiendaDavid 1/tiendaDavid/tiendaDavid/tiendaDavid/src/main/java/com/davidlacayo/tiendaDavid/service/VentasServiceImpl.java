package com.davidlacayo.tiendaDavid.service;

import com.davidlacayo.tiendaDavid.entity.Ventas;
import com.davidlacayo.tiendaDavid.exception.ResourceNotFoundException;
import com.davidlacayo.tiendaDavid.repository.VentasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentasServiceImpl implements VentasService{
    private final VentasRepository ventasRepository;

    public VentasServiceImpl(VentasRepository ventasRepository) {
        this.ventasRepository = ventasRepository;
    }

    @Override
    public List<Ventas> listarVentas() {
        return ventasRepository.findAll();
    }

    @Override
    public Ventas crearVentas(Ventas ventas) {
        return ventasRepository.save(ventas);
    }

    @Override
    public Ventas actualizarVentas(Integer id, Ventas ventas) {
        Ventas existente = buscarPorIdVentas(id);
        existente.setFechaVenta(ventas.getFechaVenta());
        existente.setTotal(ventas.getTotal());
        existente.setEstado(ventas.getEstado());
        existente.setClientesDpiCliente(ventas.getClientesDpiCliente());
        existente.setUsuariosCodigoUsuario(ventas.getUsuariosCodigoUsuario());
        return ventasRepository.save(ventas);
    }

    @Override
    public Ventas buscarPorIdVentas(Integer id) {
        return ventasRepository.findById(id).orElseThrow(() -> new RuntimeException("Ruta con codigo de venta: "+id+" no encontrada"));
    }

    @Override
    public void eliminarVentas(Integer id) {
        if (ventasRepository.existsById(id)) {
            throw new ResourceNotFoundException("Ruta con codigo de venta: "+id+" no encontrada");
        }
        ventasRepository.deleteById(id);
    }
}
