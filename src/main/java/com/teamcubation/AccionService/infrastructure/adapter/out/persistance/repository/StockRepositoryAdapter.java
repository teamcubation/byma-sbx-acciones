package com.teamcubation.AccionService.infrastructure.adapter.out.persistance.repository;

import com.teamcubation.AccionService.domain.model.Stock;
import com.teamcubation.AccionService.infrastructure.adapter.out.persistance.entity.StockEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class StockRepositoryAdapter implements StockRepositoryPort {
    private final SpringStockRepository stockRepository;

    public StockRepositoryAdapter(SpringStockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    Stock create(Stock stock) {
        StockEntity stockEntity = StockMapper.domainToEntity(stock);
        return StockMapper.entityToDomain(this.stockRepository.save(stockEntity));
    }

    Optional<Stock> findById(Long id) {
        return this.findById(id);
    }

    List<Stock> getAll() {
        return this.stockRepository
                .findAll()
                .stream()
                .map(StockMapper::entityToDomain)
                .collect(Collectors.toList());
    }

    Stock update(Stock stock) {
        StockEntity stockEntity = StockMapper.domainToEntity(stock);
        return StockMapper.entityToDomain(this.stockRepository.save(stockEntity));
    }

    void deleteById(Long id) {
        this.stockRepository.deleteById(id);
    }
}
