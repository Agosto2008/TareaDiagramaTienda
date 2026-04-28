package com.davidlacayo.tiendaDavid.service;


import com.davidlacayo.tiendaDavid.entity.Clientes;
import com.davidlacayo.tiendaDavid.exception.ResourceNotFoundException;
import com.davidlacayo.tiendaDavid.repository.ClientesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientesServiceImpl implements ClientesService{
    private final ClientesRepository clientesRepository;

    public ClientesServiceImpl(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }
    @Override
    public List<Clientes> listarClientes(){
        return clientesRepository.findAll();
    }
    @Override
    public Clientes crearClientes(Clientes clientes){
        return clientesRepository.save(clientes);
    }
    @Override
    public Clientes actualizarClientes(Integer id, Clientes clientes){
        Clientes existente = buscarPorIdClientes(id);
        existente.setNombreCliente(clientes.getNombreCliente());
        existente.setApellidoCliente(clientes.getApellidoCliente());
        existente.setDpiCliente(clientes.getDpiCliente());
        existente.setDireccion(clientes.getDireccion());
        existente.setEstado(clientes.getEstado());
        return clientesRepository.save(existente);
    }
    @Override
    public Clientes buscarPorIdClientes(Integer id){
        return clientesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ruta con dpi: "+id+" no encontrada"));
    }
    @Override
    public void eliminarClientes(Integer id) {
        if (!clientesRepository.existsById(id)) {
            throw  new ResourceNotFoundException("Ruta con dpi: "+id+" no encontrada");
        }
        clientesRepository.deleteById(id);
    }
}
