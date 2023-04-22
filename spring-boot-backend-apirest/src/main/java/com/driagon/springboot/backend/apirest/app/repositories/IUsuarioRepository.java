package com.driagon.springboot.backend.apirest.app.repositories;

import com.driagon.springboot.backend.apirest.app.models.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IUsuarioRepository extends CrudRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);

    @Query("SELECT u FROM Usuario u WHERE u.username = ?1")
    Optional<Usuario> findByUsername2(String username);
}