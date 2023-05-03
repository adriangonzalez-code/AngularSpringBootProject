package com.driagon.springboot.backend.apirest.app.controllers;

import com.driagon.springboot.backend.apirest.app.models.Factura;
import com.driagon.springboot.backend.apirest.app.models.Producto;
import com.driagon.springboot.backend.apirest.app.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/facturas")
public class FacturaRestController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Factura factura(@PathVariable Long id){
        return this.clienteService.findFacturaById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        this.clienteService.deleteFacturaById(id);
    }

    @GetMapping("/filtrar-productos/{term}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Producto> filtrarProductos(@PathVariable(required = false) String term){

        if (term == null)
            return new ArrayList<Producto>();

        return this.clienteService.findProductoByNombre(term);
    }
}