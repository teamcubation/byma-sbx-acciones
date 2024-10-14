package com.teamcubation.AccionService.application.port.in;

import com.teamcubation.AccionService.domain.exception.DuplicateStockException;
import com.teamcubation.AccionService.domain.exception.InvalidStockEntityException;
import com.teamcubation.AccionService.domain.exception.InvalidStockModelException;
import com.teamcubation.AccionService.domain.exception.StockNotFoundException;
import com.teamcubation.AccionService.domain.model.Stock;

import java.util.List;

public interface StockInPort {
    Stock create(Stock stock) throws DuplicateStockException, InvalidStockModelException, InvalidStockEntityException;
    Stock findById(long id) throws StockNotFoundException, InvalidStockModelException, InvalidStockEntityException;
    List<Stock> getAll() throws InvalidStockModelException, InvalidStockEntityException;
    Stock update(Stock stock) throws InvalidStockModelException, StockNotFoundException, DuplicateStockException, InvalidStockEntityException;
    void deleteById(long id) throws StockNotFoundException, InvalidStockModelException, InvalidStockEntityException;
}
