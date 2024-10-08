package com.teamcubation.AccionService.application.port.out;

import com.teamcubation.AccionService.domain.model.Stock;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface StockRepositoryPort {
    Stock create(Stock accion);
    Optional<Stock> findById(Long id);
    List<Stock> getAll();
    Stock update(Stock accion);
    void deleteById(Long id);
}
