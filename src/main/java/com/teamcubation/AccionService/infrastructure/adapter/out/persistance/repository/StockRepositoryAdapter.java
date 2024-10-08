package com.teamcubation.AccionService.infrastructure.adapter.out.persistance.repository;

import com.teamcubation.AccionService.domain.model.Stock;
import com.teamcubation.AccionService.infrastructure.adapter.out.persistance.entity.StockEntity;
import com.teamcubation.AccionService.infrastructure.adapter.out.persistance.mapper.StockMapper;
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

    @Override
    Stock create(Stock stock) {
        StockEntity stockEntity = StockMapper.domainToEntity(stock);
        return StockMapper.entityToDomain(this.stockRepository.save(stockEntity));
    }

    @Override
    Stock findById(Long id) {
        Optional<StockEntity> stockEntity = this.stockRepository.findById(id);

        if (stockEntity.isEmpty()){
            throw new StockNotFoundException(STOCK_NOT_FOUND);
        }

        return StockMapper.entityToDomain(stockEntity.get());
    }

    @Override
    List<Stock> getAll() {
        return this.stockRepository
                .findAll()
                .stream()
                .map(StockMapper::entityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    Stock update(Stock stock) {
        StockEntity stockEntity = StockMapper.domainToEntity(stock);
        return StockMapper.entityToDomain(this.stockRepository.save(stockEntity));
    }

    @Override
    void deleteById(Long id) {
        this.stockRepository.deleteById(id);
    }
}
