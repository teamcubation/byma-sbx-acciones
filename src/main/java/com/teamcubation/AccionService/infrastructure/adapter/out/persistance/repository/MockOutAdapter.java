package com.teamcubation.AccionService.infrastructure.adapter.out.persistance.repository;

import com.teamcubation.AccionService.application.port.out.StockOutPort;
import com.teamcubation.AccionService.domain.exception.StockNotFoundException;
import com.teamcubation.AccionService.domain.model.Stock;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MockOutAdapter implements StockOutPort {
    @Override
    public Stock create(Stock stock) {
        return null;
    }

    @Override
    public Stock findById(Long id) throws StockNotFoundException {
        return null;
    }

    @Override
    public List<Stock> getAll() {
        return List.of();
    }

    @Override
    public Stock update(Stock stock) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
