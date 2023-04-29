package com.driagon.springboot.backend.apirest.app.repositories;

import com.driagon.springboot.backend.apirest.app.models.Factura;
import org.springframework.data.repository.CrudRepository;

public interface IFacturaRepository extends CrudRepository<Factura, Long> {
}