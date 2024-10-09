package com.teamcubation.AccionService.application.port.in;

import com.teamcubation.AccionService.domain.exception.DuplicateStockException;
import com.teamcubation.AccionService.domain.exception.InvalidStockModelException;
import com.teamcubation.AccionService.domain.exception.StockNotFoundException;
import com.teamcubation.AccionService.domain.model.Stock;

import java.util.List;

public interface StockInPort {
    Stock create(Stock stock) throws DuplicateStockException, InvalidStockModelException;
    Stock findById(long id) throws StockNotFoundException;
    List<Stock> getAll();
    Stock update(Stock stock) throws InvalidStockModelException, StockNotFoundException, DuplicateStockException;
    void deleteById(long id) throws StockNotFoundException;
}
