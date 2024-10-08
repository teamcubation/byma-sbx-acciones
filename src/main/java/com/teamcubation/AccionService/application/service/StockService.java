package com.teamcubation.AccionService.application.service;

import com.teamcubation.AccionService.application.port.in.StockInPort;
import com.teamcubation.AccionService.application.port.out.StockRepositoryPort;
import com.teamcubation.AccionService.domain.exception.DuplicateStockException;
import com.teamcubation.AccionService.domain.exception.InvalidStockModelException;
import com.teamcubation.AccionService.domain.exception.StockNotFoundException;
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
    public Stock create(Stock accion) throws DuplicateStockException, InvalidStockModelException {

        if (isDuplicated(accion.getName())) {
            throw new DuplicateStockException("Stock with that name already exists");
        }

        if (isInvalid(accion)) {
            throw new InvalidStockModelException("Invalid stock data");
        }

        return this.accionRepositoryPort.create(accion);
    }

    @Override
    public Optional<Stock> findById(Long id) throws StockNotFoundException {
        if (isNotFound(id)) {
            throw new StockNotFoundException("Stock not found");
        }
        return this.accionRepositoryPort.findById(id);
    }

    @Override
    public List<Stock> getAll() {
        return this.accionRepositoryPort.getAll();
    }

    @Override
    public Stock update(Stock accion) throws InvalidStockModelException, StockNotFoundException {
        if (isInvalid(accion)) {
            throw new InvalidStockModelException("Invalid stock data");
        }
        if (isNotFound(accion.getId())) {
            throw new StockNotFoundException("Stock not found");
        }
        return this.accionRepositoryPort.update(accion);
    }

    @Override
    public void deleteById(Long id) throws StockNotFoundException {
        if (isNotFound(id)) {
            throw new StockNotFoundException("Stock not found");
        }
        this.accionRepositoryPort.deleteById(id);
    }

    //validations

    private boolean isNotFound(Long id) {
        return this.accionRepositoryPort.findById(id).isEmpty();
    }

    private boolean isDuplicated(String name) {
        return this.accionRepositoryPort
                .getAll()
                .stream()
                .anyMatch(accion -> accion.getName().equals(name));
    }

    private boolean isInvalid(Stock accion) {
        return accion.getName() == null || accion.getPrice() < 0 || accion.getDividend() < 0;
    }
}
