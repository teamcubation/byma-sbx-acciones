package com.teamcubation.AccionService.application.port.out;

import com.teamcubation.AccionService.domain.exception.InvalidStockEntityException;
import com.teamcubation.AccionService.domain.exception.InvalidStockModelException;
import com.teamcubation.AccionService.domain.exception.StockNotFoundException;
import com.teamcubation.AccionService.domain.model.Stock;


import java.util.List;

public interface StockOutPort {
    Stock create(Stock stock) throws InvalidStockModelException, InvalidStockEntityException;
    Stock findById(long id) throws StockNotFoundException, InvalidStockEntityException;
    List<Stock> getAll() throws InvalidStockEntityException;
    Stock update(Stock stock) throws InvalidStockModelException, InvalidStockEntityException;
    void deleteById(long id);
}