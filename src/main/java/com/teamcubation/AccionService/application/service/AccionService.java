package com.teamcubation.AccionService.application.service;

import com.teamcubation.AccionService.application.port.in.AccionInPort;
import com.teamcubation.AccionService.application.port.out.AccionRepositoryPort;
import com.teamcubation.AccionService.domain.model.Accion;

import java.util.List;
import java.util.Optional;

public class AccionService implements AccionInPort {

    private final AccionRepositoryPort accionRepositoryPort;

    public AccionService(AccionRepositoryPort accionRepositoryPort) {
        this.accionRepositoryPort = accionRepositoryPort;
    }

    @Override
    public Accion save(Accion accion) {
        return this.accionRepositoryPort.save(accion);
    }

    @Override
    public Optional<Accion> findById(Long id) {
        return this.accionRepositoryPort.findById(id);
    }

    @Override
    public List<Accion> findAll() {
        return this.accionRepositoryPort.findAll();
    }

    @Override
    public Accion update(Accion accion) {
        return this.accionRepositoryPort.update(accion);
    }

    @Override
    public void deleteById(Long id) {
        this.accionRepositoryPort.deleteById(id);
    }
}
