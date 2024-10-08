package com.teamcubation.AccionService.application.port.in;

import com.teamcubation.AccionService.domain.model.Stock;

import java.util.List;
import java.util.Optional;

public interface StockInPort {

    Stock create(Stock accion);

    Optional<Stock> findById(Long id);

    List<Stock> getAll();

    Stock update(Stock accion);

    void deleteById(Long id);

}
