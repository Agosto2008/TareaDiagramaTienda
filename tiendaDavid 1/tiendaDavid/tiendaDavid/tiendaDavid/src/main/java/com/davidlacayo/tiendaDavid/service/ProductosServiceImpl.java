package com.davidlacayo.tiendaDavid.service;


import com.davidlacayo.tiendaDavid.entity.Productos;
import com.davidlacayo.tiendaDavid.exception.ResourceNotFoundException;
import com.davidlacayo.tiendaDavid.repository.ProductosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductosServiceImpl implements ProductosService{

    private final ProductosRepository productosRepository;

    public ProductosServiceImpl(ProductosRepository productosRepository) {
        this.productosRepository = productosRepository;
    }

    @Override
    public List<Productos> listarProductos(){
        return productosRepository.findAll();
    }

    @Override
    public Productos crearProductos(Productos productos){
        return productosRepository.save(productos);
    }

    @Override
    public Productos actualizarProductos(Integer id, Productos productos){
        Productos existente = buscarPorIdProductos(id);
        existente.setNombreProducto(productos.getNombreProducto());
        existente.setPrecio(productos.getPrecio());
        existente.setStock(productos.getStock());
        existente.setEstado(productos.getEstado());
        return productosRepository.save(existente);
    }

    @Override
    public Productos buscarPorIdProductos(Integer id){
        return productosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ruta con codigo de producto: "+id+" no encontrada"));
    }

    @Override
    public void eliminarProductos(Integer id) {
        if (!productosRepository.existsById(id)) {
            throw  new ResourceNotFoundException("Ruta con codigo de producto: "+id+" no encontrada");
        }
        productosRepository.deleteById(id);
    }
}
