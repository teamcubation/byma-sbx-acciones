package com.teamcubation.AccionService.application.port.out;

import com.teamcubation.AccionService.domain.model.Stock;
import java.util.List;

public interface StockRepositoryPort {
    //TODO: Agregar las custom exception cuando esten definidas
    Stock create(Stock stock) throws Exception;
    Stock findById(long id) throws Exception;
    List<Stock> getAll() throws Exception;
    Stock update(Stock stock) throws Exception;
    void deleteById(long id);
}
