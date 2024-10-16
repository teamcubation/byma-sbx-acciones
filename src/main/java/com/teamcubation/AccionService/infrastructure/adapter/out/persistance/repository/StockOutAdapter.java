package com.teamcubation.AccionService.infrastructure.adapter.out.persistance.repository;

import com.teamcubation.AccionService.application.port.out.StockOutPort;
import com.teamcubation.AccionService.domain.exception.*;
import com.teamcubation.AccionService.domain.model.Stock;
import com.teamcubation.AccionService.infrastructure.adapter.out.persistance.entity.StockEntity;
import com.teamcubation.AccionService.infrastructure.adapter.out.persistance.mapper.StockMapper;
import com.teamcubation.AccionService.infrastructure.adapter.out.persistance.util.validation.OutValidation;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class StockOutAdapter implements StockOutPort {

    private final SpringStockRepository stockRepository;

    public StockOutAdapter(SpringStockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public Stock create(Stock stock) throws InvalidStockModelException, InvalidStockEntityException {
        OutValidation.validateStockIsNull(stock);

        StockEntity stockEntity = StockMapper.domainToEntity(stock);

        StockEntity newStockEntity = this.stockRepository.save(stockEntity);

        return StockMapper.entityToDomain(newStockEntity);
    }

    @Override
    public Stock findById(long id) throws StockNotFoundException, InvalidStockEntityException {
        Optional<StockEntity> stockEntity = this.stockRepository.findById(id);

        if (stockEntity.isEmpty()){
            log.error(OutValidation.STOCK_NOT_FOUND);
            throw new StockNotFoundException(OutValidation.STOCK_NOT_FOUND);
        }

        return StockMapper.entityToDomain(stockEntity.get());
    }

    @Override
    public List<Stock> getAll() throws InvalidStockEntityException {
        List<Stock> stocks = new ArrayList<>();

        for (StockEntity stockEntity: this.stockRepository.findAll()) {
            stocks.add(StockMapper.entityToDomain(stockEntity));
        }

        return stocks;
    }

    @Override
    public Stock update(@Valid Stock stock) throws InvalidStockModelException, InvalidStockEntityException {
        OutValidation.validateStockIsNull(stock);

        StockEntity stockEntity = StockMapper.domainToEntity(stock);

        return StockMapper.entityToDomain(this.stockRepository.save(stockEntity));
    }

    @Override
    public void deleteById(long id) {
        this.stockRepository.deleteById(id);
    }
}
