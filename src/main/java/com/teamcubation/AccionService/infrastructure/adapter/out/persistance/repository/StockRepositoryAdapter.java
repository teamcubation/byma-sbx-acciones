package com.teamcubation.AccionService.infrastructure.adapter.out.persistance.repository;

import com.teamcubation.AccionService.application.port.out.StockRepositoryPort;
import com.teamcubation.AccionService.domain.model.Stock;
import com.teamcubation.AccionService.infrastructure.adapter.out.persistance.entity.StockEntity;
import com.teamcubation.AccionService.infrastructure.adapter.out.persistance.mapper.StockMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StockRepositoryAdapter implements StockRepositoryPort {
    private final SpringStockRepository stockRepository;

    public StockRepositoryAdapter(SpringStockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    //TODO: Agregar las custom exception cuando esten definidas
    @Override
    public Stock create(Stock stock) throws Exception {
        if (stock == null) {
            throw new Exception("STOCK_NOT_FOUND");
        }

        StockEntity stockEntity = StockMapper.domainToEntity(stock);

        return StockMapper.entityToDomain(this.stockRepository.save(stockEntity));
    }

    //TODO: Agregar las custom exception cuando esten definidas
    @Override
    public Stock findById(long id) throws Exception {
        Optional<StockEntity> stockEntity = this.stockRepository.findById(id);

        if (stockEntity.isEmpty()){
            throw new Exception("STOCK_NOT_FOUND");
        }

        return StockMapper.entityToDomain(stockEntity.get());
    }

    //TODO: Agregar las custom exception cuando esten definidas
    @Override
    public List<Stock> getAll() throws Exception {
        List<Stock> stocks = new ArrayList<>();

        for (StockEntity stockEntity: this.stockRepository.findAll()) {
            stocks.add(StockMapper.entityToDomain(stockEntity));
        }

        return stocks;
    }

    //TODO: Agregar las custom exception cuando esten definidas
    @Override
    public Stock update(Stock stock) throws Exception {
        if (stock == null) {
            throw new Exception("STOCK_NOT_FOUND");
        }

        StockEntity stockEntity = StockMapper.domainToEntity(stock);

        return StockMapper.entityToDomain(this.stockRepository.save(stockEntity));
    }

    @Override
    public void deleteById(long id) {
        this.stockRepository.deleteById(id);
    }
}
