package com.davidlacayo.tiendaDavid.service;


import com.davidlacayo.tiendaDavid.entity.DetalleVenta;
import com.davidlacayo.tiendaDavid.exception.ResourceNotFoundException;
import com.davidlacayo.tiendaDavid.repository.DetalleVentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService {
    private final DetalleVentaRepository detalleVentaRepository;

    public DetalleVentaServiceImpl(DetalleVentaRepository detalleVentaRepository) {
        this.detalleVentaRepository = detalleVentaRepository;
    }

    @Override
    public List<DetalleVenta> listarDetalleVentas() {
        return detalleVentaRepository.findAll();
    }

    @Override
    public DetalleVenta crearDetalleVenta(DetalleVenta detalleVenta) {
        return detalleVentaRepository.save(detalleVenta);
    }

    @Override
    public DetalleVenta actualizarDetalleVenta(Integer id, DetalleVenta detalleVenta) {
        DetalleVenta existente = buscarPorIdDetalleVenta(id);
        existente.setCantidad(detalleVenta.getCantidad());
        existente.setSubtotal(detalleVenta.getSubtotal());
        existente.setProductosCodigoProducto(detalleVenta.getProductosCodigoProducto());
        existente.setVentasCodigoVenta(existente.getVentasCodigoVenta());
        return detalleVentaRepository.save(existente);
    }

    @Override
    public DetalleVenta buscarPorIdDetalleVenta(Integer id) {
        return detalleVentaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ruta con codigo de detalle de venta: "+id+" no encontrada"));
    }

    @Override
    public void eliminarDetalleVenta(Integer id){
        if(!detalleVentaRepository.existsById(id)){
            throw new ResourceNotFoundException("El detalle de venta no existe");
        }
        detalleVentaRepository.deleteById(id);
    }
}
