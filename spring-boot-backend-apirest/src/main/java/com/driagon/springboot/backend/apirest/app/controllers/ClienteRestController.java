package com.driagon.springboot.backend.apirest.app.controllers;

import com.driagon.springboot.backend.apirest.app.models.Cliente;
import com.driagon.springboot.backend.apirest.app.services.IClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/clientes")
public class ClienteRestController {

    private final Logger log = LoggerFactory.getLogger(ClienteRestController.class);

    @Autowired
    private IClienteService service;

    @GetMapping("")
    public List<Cliente> index() {
        return this.service.findAll();
    }

    @GetMapping("/page/{page}")
    public Page<Cliente> index(@PathVariable("page") Integer page) {
        Pageable pageable = PageRequest.of(page, 4);
        return this.service.findAll(pageable);
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
    public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {

            /*List<String> errors = new ArrayList<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.add("El campo '" + error.getField() + "' " + error.getDefaultMessage());
            }*/

            List<String> errors = result.getFieldErrors().stream().map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage()).collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Cliente clienteNew = null;

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
    public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable("id") Long id) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors().stream().map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage()).collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Cliente clienteActual = this.service.findById(id);
        Cliente clienteUpdated = null;

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
            Cliente cliente = this.service.findById(id);
            String nombreFotoAnterior = cliente.getFoto();

            if (nombreFotoAnterior != null && nombreFotoAnterior.length() > 0) {
                Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
                File archivoFotoAnterior = rutaFotoAnterior.toFile();
                if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
                    archivoFotoAnterior.delete();
                }
            }

            this.service.delete(id);
        } catch (DataAccessException ex) {
            response.put("mensaje", "Error al eliminar el cliente de la Base de Datos");
            response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El cliente ha sido eliminado con éxito");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {
        Map<String, Object> response = new HashMap<>();
        Cliente cliente = this.service.findById(id);

        if (!archivo.isEmpty()) {
            String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
            Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();

            log.info(rutaArchivo.toString());
            try {
                Files.copy(archivo.getInputStream(), rutaArchivo);
            } catch (IOException ex) {
                response.put("mensaje", "Error al subir la imagen del cliente " + nombreArchivo);
                response.put("error", ex.getMessage().concat(": ").concat(ex.getCause().getMessage()));
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            String nombreFotoAnterior = cliente.getFoto();
            if (nombreFotoAnterior != null && nombreFotoAnterior.length() > 0) {
                Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
                File archivoFotoAnterior = rutaFotoAnterior.toFile();
                if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
                    archivoFotoAnterior.delete();
                }
            }

            cliente.setFoto(nombreArchivo);
            this.service.save(cliente);

            response.put("cliente", cliente);
            response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
        }

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/upload/img/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {
        Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
        log.info(rutaArchivo.toString());
        Resource recurso = null;

        try {
            recurso = new UrlResource(rutaArchivo.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if (!recurso.exists() && recurso.isReadable()) {
            throw new RuntimeException("No se pudo cargar la imagen: " + nombreFoto);
        }

        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

        return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
    }
}