package com.driagon.springboot.backend.apirest.app.repositories;

import com.driagon.springboot.backend.apirest.app.models.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface IClienteRepository extends CrudRepository<Cliente, Long> {
}