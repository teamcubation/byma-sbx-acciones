package com.teamcubation.AccionService.application.service;

import com.teamcubation.AccionService.application.port.in.StockInPort;
import com.teamcubation.AccionService.application.port.out.StockRepositoryPort;
import com.teamcubation.AccionService.domain.exception.DuplicateStockException;
import com.teamcubation.AccionService.domain.exception.InvalidStockModelException;
import com.teamcubation.AccionService.domain.exception.StockNotFoundException;
import com.teamcubation.AccionService.domain.model.Stock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StockService implements StockInPort {

    private final StockRepositoryPort accionRepositoryPort;

    public StockService(StockRepositoryPort accionRepositoryPort) {
        this.accionRepositoryPort = accionRepositoryPort;
    }

    @Override
    public Stock create(Stock stock) throws DuplicateStockException, InvalidStockModelException {

        if (isNull(stock)) {
            log.error("Invalid stock data");
            throw new InvalidStockModelException("Invalid stock data");
        }
        if (isInvalid(stock)) {
            log.error("Invalid stock data");
            throw new InvalidStockModelException("Invalid stock data");
        }

        if (isDuplicated(stock.getName())) {

            log.error("Stock with name {} already exists", stock.getName());
            throw new DuplicateStockException("Stock with that name already exists");
        }



        return this.accionRepositoryPort.create(stock);
    }



    @Override
    public Optional<Stock> findById(long id) throws StockNotFoundException, InvalidStockModelException {

        if (isNotFound(id)) {
            log.error("Stock with id {} not found", id);
            throw new StockNotFoundException("Stock not found");
        }
        return this.accionRepositoryPort.findById(id);
    }

    @Override
    public List<Stock> getAll() {
        return this.accionRepositoryPort.getAll();
    }

    @Override
    public Stock update(Stock stock) throws InvalidStockModelException, StockNotFoundException {

        if (isNull(stock)) {
            log.error("Invalid stock data");
            throw new InvalidStockModelException("Invalid stock data");
        }

        if (isInvalid(stock)) {

            log.error("Invalid stock data");
            throw new InvalidStockModelException("Invalid stock data");
        }
        if (isNotFound(stock.getId())) {
            log.error("Stock with id {} not found", stock.getId());
            throw new StockNotFoundException("Stock not found");
        }
        return this.accionRepositoryPort.update(stock);
    }

    @Override
    public void deleteById(long id) throws StockNotFoundException, InvalidStockModelException {


        if (isNotFound(id)) {
            log.error("Stock with id {} not found", id);
            throw new StockNotFoundException("Stock not found");
        }
        this.accionRepositoryPort.deleteById(id);
    }

    //validations
    private boolean isNull(Object... stock) {

        for (Object o : stock) {
            if (o == null) {
                return true;
            }
        }
        return false;
    }
    private boolean isNotFound(Long id) {
        return this.accionRepositoryPort.findById(id).isEmpty();
    }

    private boolean isDuplicated(String name) {
        return this.accionRepositoryPort
                .getAll()
                .stream()
                .anyMatch(accion -> accion.getName().equals(name));
    }

    private boolean isInvalid(Stock stock) {

        return stock.getName() == null || stock.getPrice() < 0 || stock.getDividend() < 0;
    }
}
