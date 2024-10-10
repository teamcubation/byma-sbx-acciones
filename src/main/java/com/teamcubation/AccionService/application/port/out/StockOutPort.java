package com.teamcubation.AccionService.application.port.out;

import com.teamcubation.AccionService.domain.exception.StockNotFoundException;
import com.teamcubation.AccionService.domain.model.Stock;
import java.util.List;

public interface StockOutPort {
    Stock create(Stock stock);
    Stock findById(Long id) throws StockNotFoundException;
    List<Stock> getAll();
    Stock update(Stock stock);
    void deleteById(Long id);
}