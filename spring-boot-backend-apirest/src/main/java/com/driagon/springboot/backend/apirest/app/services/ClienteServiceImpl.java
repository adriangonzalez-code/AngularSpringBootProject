package com.driagon.springboot.backend.apirest.app.services;

import com.driagon.springboot.backend.apirest.app.models.Cliente;
import com.driagon.springboot.backend.apirest.app.models.Factura;
import com.driagon.springboot.backend.apirest.app.models.Region;
import com.driagon.springboot.backend.apirest.app.repositories.IClienteRepository;
import com.driagon.springboot.backend.apirest.app.repositories.IFacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private IClienteRepository repository;

    @Autowired
    private IFacturaRepository facturaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return (List<Cliente>) this.repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Cliente> findAll(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findById(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Cliente save(Cliente cliente) {
        return this.repository.save(cliente);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Region> findAllRegiones() {
        return this.repository.findAllRegiones();
    }

    @Override
    @Transactional(readOnly = true)
    public Factura findFacturaById(Long id) {
        return this.facturaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Factura saveFactura(Factura factura) {
        return this.facturaRepository.save(factura);
    }

    @Override
    @Transactional
    public void deleteFacturaById(Long id) {
        this.facturaRepository.deleteById(id);
    }
}