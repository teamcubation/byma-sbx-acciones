package com.teamcubation.AccionService.application.port.out;

import com.teamcubation.AccionService.domain.exception.StockNotFoundException;
import com.teamcubation.AccionService.domain.model.Stock;
import java.util.List;

public interface StockRepositoryPort {
    Stock create(Stock stock);
    Stock findById(long id) throws StockNotFoundException;
    List<Stock> getAll();
    Stock update(Stock stock);
    void deleteById(long id);
}
