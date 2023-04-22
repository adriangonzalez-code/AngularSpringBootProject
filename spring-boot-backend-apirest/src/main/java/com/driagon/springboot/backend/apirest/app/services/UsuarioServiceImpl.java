package com.driagon.springboot.backend.apirest.app.services;

import com.driagon.springboot.backend.apirest.app.models.Usuario;
import com.driagon.springboot.backend.apirest.app.repositories.IUsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UserDetailsService, IUsuarioService {

    private Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @Autowired
    private IUsuarioRepository repository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> usuarioOptional = repository.findByUsername(username);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            List<GrantedAuthority> authorities = usuario.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).peek(authority -> logger.info("Role: " + authority.getAuthority())).collect(Collectors.toList());
            return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
        }

        logger.error("Error en el login:  no existe el usuario '" + username + "' en el sistema");
        throw new UsernameNotFoundException("No existe el usuario '" + username + "' en el sistema");
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findByUsername(String username) {
        return this.repository.findByUsername(username).orElse(null);
    }
}
