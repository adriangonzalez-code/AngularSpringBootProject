package com.driagon.springboot.backend.apirest.app.services;

import com.driagon.springboot.backend.apirest.app.models.Usuario;

public interface IUsuarioService {

    Usuario findByUsername(String username);
}