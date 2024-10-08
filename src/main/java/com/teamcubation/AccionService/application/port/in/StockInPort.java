package com.teamcubation.AccionService.application.port.in;

import com.teamcubation.AccionService.domain.exception.DuplicateStockException;
import com.teamcubation.AccionService.domain.exception.InvalidStockModelException;
import com.teamcubation.AccionService.domain.exception.StockNotFoundException;
import com.teamcubation.AccionService.domain.model.Stock;

import java.util.List;
import java.util.Optional;


public interface StockInPort {

    Stock create(Stock accion) throws DuplicateStockException, InvalidStockModelException;

    Optional<Stock> findById(Long id) throws StockNotFoundException;

    List<Stock> getAll();

    Stock update(Stock accion) throws InvalidStockModelException, StockNotFoundException;

    void deleteById(Long id) throws StockNotFoundException;

}
