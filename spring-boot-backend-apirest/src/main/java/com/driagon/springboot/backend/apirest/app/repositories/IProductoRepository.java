package com.driagon.springboot.backend.apirest.app.repositories;

import com.driagon.springboot.backend.apirest.app.models.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IProductoRepository extends CrudRepository<Producto, Long> {

    @Query("SELECT p FROM Producto p WHERE p.nombre LIKE %?1%")
    List<Producto> findByNombre(String term);

    List<Producto> findByNombreContainsIgnoreCase(String term);


}