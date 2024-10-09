package com.teamcubation.AccionService.application.service;

import com.teamcubation.AccionService.application.port.in.StockInPort;
import com.teamcubation.AccionService.application.port.out.StockOutPort;
import com.teamcubation.AccionService.domain.exception.DuplicateStockException;
import com.teamcubation.AccionService.domain.exception.InvalidStockModelException;
import com.teamcubation.AccionService.domain.exception.StockNotFoundException;
import com.teamcubation.AccionService.domain.model.Stock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.teamcubation.AccionService.application.service.util.validation.ServiceValidation.*;

@Slf4j
public class StockService implements StockInPort {

    private final StockOutPort stockOutPort;


    public StockService(StockOutPort stockOutPort) {
        this.stockOutPort = stockOutPort;
    }

    /**
     * Create a new stock with the given parameters.
     *
     * @param stock The stock to create.
     * @return The created stock.
     * @throws DuplicateStockException If a stock with the same name already exists.
     * @throws InvalidStockModelException If the stock has invalid data or is null.
     */
    @Override
    public Stock create(Stock stock) throws DuplicateStockException, InvalidStockModelException {
        this.validateStockIsNull(stock);
        this.validateStockIsValid(stock);
        this.validateStockIsDuplicated(stock);
        return this.stockOutPort.create(stock);
    }

    @Override
    public Stock findById(long id) throws StockNotFoundException {
        validateStockIsNotFound(id);
        return this.stockOutPort.findById(id);
    }


    /**
     * Retrieves all stocks.
     *
     * @return A list of all stocks.
     */
    @Override
    public List<Stock> getAll() {
        return this.stockOutPort.getAll();
    }

    /**
     * Updates a stock.
     *
     * @param stock The stock to update.
     * @return The updated stock.
     * @throws InvalidStockModelException If the stock has invalid data or is null.
     * @throws StockNotFoundException If no stock with the given ID exists.
     * @throws DuplicateStockException If a stock with the same name already exists.
     */
    @Override
    public Stock update(Stock stock) throws InvalidStockModelException, StockNotFoundException, DuplicateStockException {
        this.validateStockIsNull(stock);
        this.validateStockIsValid(stock);
        this.validateStockIsNotFound(stock.getId());
        this.validateStockIsDuplicated(stock);
        return this.stockOutPort.update(stock);
    }

    /**
     * Deletes a stock by its ID.
     *
     * @param id The ID of the stock to delete.
     * @throws StockNotFoundException If no stock with the given ID exists.
     */
    @Override
    public void deleteById(long id) throws StockNotFoundException {
        this.validateStockIsNotFound(id);
        this.stockOutPort.deleteById(id);
    }

    //validations
    private void validateStockIsNull(Stock stock) throws InvalidStockModelException {
        if (isNull(stock)) {
            log.error(INVALID_STOCK_DATA);
            throw new InvalidStockModelException(INVALID_STOCK_DATA);
        }
    }

    private void validateStockIsValid(Stock stock) throws InvalidStockModelException {
        if (isValid(stock)) {
            log.error(INVALID_STOCK_DATA);
            throw new InvalidStockModelException(INVALID_STOCK_DATA);
        }
    }

    private void validateStockIsDuplicated(Stock stock) throws DuplicateStockException {
        if (isDuplicated(stock.getName())) {
            log.error(STOCK_WITH_NAME_ALREADY_EXISTS, stock.getName());
            throw new DuplicateStockException(STOCK_WITH_NAME_ALREADY_EXISTS);
        }
    }

    private void validateStockIsNotFound(long id) throws StockNotFoundException {
        if (isNotFound(id)) {
            log.error(STOCK_NOT_FOUND);
            throw new StockNotFoundException(STOCK_NOT_FOUND);
        }
    }


    private boolean isNotFound(Long id) throws StockNotFoundException {
        return this.stockOutPort.findById(id) == null;
    }

    private boolean isDuplicated(String name) {
        return this.stockOutPort
                .getAll()
                .stream()
                .anyMatch(stock -> stock.getName().equals(name));
    }


}
