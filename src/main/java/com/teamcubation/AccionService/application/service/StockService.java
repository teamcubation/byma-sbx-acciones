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

import static com.teamcubation.AccionService.application.util.validation.ServiceValidation.*;

@Slf4j
@Service
public class StockService implements StockInPort {


    private final StockRepositoryPort accionRepositoryPort;


    public StockService(StockRepositoryPort accionRepositoryPort) {
        this.accionRepositoryPort = accionRepositoryPort;
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
        this.modelIsNull(stock);
        this.modelIsInvalid(stock);
        this.modelIsDuplicated(stock);
        return this.accionRepositoryPort.create(stock);
    }


    /**
     * Finds a stock by its ID.
     *
     * @param id The ID of the stock to find.
     * @return An optional containing the stock if found, or an empty optional
     * otherwise.
     * @throws StockNotFoundException If no stock with the given ID exists.
     */
    @Override
    public Optional<Stock> findById(long id) throws StockNotFoundException {
        this.modelIsNotFound(id);
        return this.accionRepositoryPort.findById(id);
    }


    /**
     * Retrieves all stocks.
     *
     * @return A list of all stocks.
     */
    @Override
    public List<Stock> getAll() {
        return this.accionRepositoryPort.getAll();
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
        this.modelIsNull(stock);
        this.modelIsInvalid(stock);
        this.modelIsNotFound(stock.getId());
        this.modelIsDuplicated(stock);
        return this.accionRepositoryPort.update(stock);
    }

    /**
     * Deletes a stock by its ID.
     *
     * @param id The ID of the stock to delete.
     * @throws StockNotFoundException If no stock with the given ID exists.
     */
    @Override
    public void deleteById(long id) throws StockNotFoundException {
        this.modelIsNotFound(id);
        this.accionRepositoryPort.deleteById(id);
    }

    //validations
    private void modelIsNull(Stock stock) throws InvalidStockModelException {
        if (isNull(stock)) {
            log.error(INVALID_STOCK_DATA);
            throw new InvalidStockModelException(INVALID_STOCK_DATA);
        }
    }

    private void modelIsInvalid(Stock stock) throws InvalidStockModelException {
        if (isInvalid(stock)) {
            log.error(INVALID_STOCK_DATA);
            throw new InvalidStockModelException(INVALID_STOCK_DATA);
        }
    }

    private void modelIsDuplicated(Stock stock) throws DuplicateStockException {
        if (isDuplicated(stock.getName())) {
            log.error(STOCK_WITH_NAME_ALREADY_EXISTS, stock.getName());
            throw new DuplicateStockException(STOCK_WITH_NAME_ALREADY_EXISTS);
        }
    }

    private void modelIsNotFound(long id) throws StockNotFoundException {
        if (isNotFound(id)) {
            log.error(STOCK_NOT_FOUND);
            throw new StockNotFoundException(STOCK_NOT_FOUND);
        }
    }


    private boolean isNotFound(Long id) {
        return this.accionRepositoryPort.findById(id).isEmpty();
    }

    private boolean isDuplicated(String name) {
        return this.accionRepositoryPort
                .getAll()
                .stream()
                .anyMatch(stock -> stock.getName().equals(name));
    }


}
