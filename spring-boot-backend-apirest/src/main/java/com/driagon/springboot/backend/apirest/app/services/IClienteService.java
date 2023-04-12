package com.driagon.springboot.backend.apirest.app.services;

import com.driagon.springboot.backend.apirest.app.models.Cliente;

import java.util.List;

public interface IClienteService {

    List<Cliente> findAll();

    Cliente findById(Long id);

    Cliente save(Cliente cliente);

    void delete(Long id);
}