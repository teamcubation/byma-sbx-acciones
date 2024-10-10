package com.teamcubation.AccionService.application.port.out;

import com.teamcubation.AccionService.domain.exception.InvalidStockModelException;
import com.teamcubation.AccionService.domain.exception.StockNotFoundException;
import com.teamcubation.AccionService.domain.model.Stock;
import java.util.List;

public interface StockOutPort {
    Stock create(Stock stock) throws InvalidStockModelException;
    Stock findById(long id) throws StockNotFoundException, InvalidStockModelException;
    List<Stock> getAll() throws InvalidStockModelException;
    Stock update(Stock stock) throws InvalidStockModelException;
    void deleteById(long id);
}