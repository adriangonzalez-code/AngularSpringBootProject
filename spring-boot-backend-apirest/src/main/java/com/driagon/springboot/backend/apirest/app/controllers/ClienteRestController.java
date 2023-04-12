package com.driagon.springboot.backend.apirest.app.controllers;

import com.driagon.springboot.backend.apirest.app.models.Cliente;
import com.driagon.springboot.backend.apirest.app.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/clientes")
public class ClienteRestController {

    @Autowired
    private IClienteService service;

    @GetMapping("")
    public List<Cliente> index() {
        return this.service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable("id") Long id) {
        Cliente cliente = null;
        Map<String, Object> response = new HashMap<>();

        try {
             cliente = this.service.findById(id);
        } catch (DataAccessException ex) {
            response.put("mensaje", "Error al realizar la consulta a la Base de Datos");
            response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (cliente == null) {
            response.put("mensaje", "El cliente con el ID: ".concat(id.toString()).concat(" no existe en la Base de Datos!"));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @PostMapping("")
    /*@ResponseStatus(HttpStatus.CREATED)*/
    public ResponseEntity<?> create(@RequestBody Cliente cliente) {

        Cliente clienteNew = null;
        Map<String, Object> response = new HashMap<>();

        try {
            clienteNew = this.service.save(cliente);
        } catch (DataAccessException ex) {
            response.put("mensaje", "Error al realizar el insert en la Base de Datos");
            response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El cliente ha sido creado con éxito");
        response.put("cliente", clienteNew);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    /*@ResponseStatus(HttpStatus.CREATED)*/
    public ResponseEntity<?> update(@RequestBody Cliente cliente, @PathVariable("id") Long id) {
        Cliente clienteActual = this.service.findById(id);
        Cliente clienteUpdated = null;
        Map<String, Object> response = new HashMap<>();

        if (clienteActual == null) {
            response.put("mensaje", "Error, no se pudo editar, el cliente ID: ".concat(id.toString()).concat(" no existe en la Base de Datos!"));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        try {
            clienteActual.setApellido(cliente.getApellido());
            clienteActual.setNombre(cliente.getNombre());
            clienteActual.setEmail(cliente.getEmail());
            clienteActual.setCreateAt(cliente.getCreateAt());

            clienteUpdated = this.service.save(clienteActual);
        } catch (DataAccessException ex) {
            response.put("mensaje", "Error al actualizar el cliente en la Base de Datos");
            response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El cliente ha sido actualizado con éxito");
        response.put("cliente", clienteUpdated);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    /*@ResponseStatus(HttpStatus.NO_CONTENT)*/
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            this.service.delete(id);
        } catch (DataAccessException ex) {
            response.put("mensaje", "Error al eliminar el cliente de la Base de Datos");
            response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El cliente ha sido eliminado con éxito");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}