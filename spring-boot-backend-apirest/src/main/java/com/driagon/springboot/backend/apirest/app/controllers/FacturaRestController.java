package com.driagon.springboot.backend.apirest.app.controllers;

import com.driagon.springboot.backend.apirest.app.models.Factura;
import com.driagon.springboot.backend.apirest.app.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}