package com.teamcubation.AccionService.application.service;

import com.teamcubation.AccionService.application.port.in.StockInPort;
import com.teamcubation.AccionService.application.port.out.StockRepositoryPort;
import com.teamcubation.AccionService.domain.model.Stock;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService implements StockInPort {

    private final StockRepositoryPort accionRepositoryPort;

    public StockService(StockRepositoryPort accionRepositoryPort) {
        this.accionRepositoryPort = accionRepositoryPort;
    }

    @Override
    public Stock create(Stock accion) {
        return this.accionRepositoryPort.create(accion);
    }

    @Override
    public Optional<Stock> findById(Long id) {
        return this.accionRepositoryPort.findById(id);
    }

    @Override
    public List<Stock> getAll() {
        return this.accionRepositoryPort.getAll();
    }

    @Override
    public Stock update(Stock accion) {
        return this.accionRepositoryPort.update(accion);
    }

    @Override
    public void deleteById(Long id) {
        this.accionRepositoryPort.deleteById(id);
    }
}
