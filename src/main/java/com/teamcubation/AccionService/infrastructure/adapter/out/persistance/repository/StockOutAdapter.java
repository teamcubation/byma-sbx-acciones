package com.teamcubation.AccionService.infrastructure.adapter.out.persistance.repository;

import com.teamcubation.AccionService.application.port.out.StockOutPort;
import com.teamcubation.AccionService.domain.exception.InvalidStockModelException;
import com.teamcubation.AccionService.domain.exception.StockNotFoundException;
import com.teamcubation.AccionService.domain.model.Stock;
import com.teamcubation.AccionService.infrastructure.adapter.out.persistance.entity.StockEntity;
import com.teamcubation.AccionService.infrastructure.adapter.out.persistance.mapper.StockMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.teamcubation.AccionService.application.service.util.validation.ServiceValidation.INVALID_STOCK_DATA;
import static com.teamcubation.AccionService.application.service.util.validation.ServiceValidation.STOCK_NOT_FOUND;

@Repository
@Slf4j
public class StockOutAdapter implements StockOutPort {
    private final SpringStockRepository stockRepository;

    public StockOutAdapter(SpringStockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public Stock create(Stock stock) throws InvalidStockModelException {
        this.validateStockIsNull(stock);

        StockEntity stockEntity = StockMapper.domainToEntity(stock);

        return StockMapper.entityToDomain(this.stockRepository.save(stockEntity));
    }

    @Override
    public Stock findById(long id) throws StockNotFoundException, InvalidStockModelException {
        Optional<StockEntity> stockEntity = this.stockRepository.findById(id);

        if (stockEntity.isEmpty()){
            log.error(STOCK_NOT_FOUND);
            throw new StockNotFoundException(STOCK_NOT_FOUND);
        }

        return StockMapper.entityToDomain(stockEntity.get());
    }

    @Override
    public List<Stock> getAll() throws InvalidStockModelException {
        List<Stock> stocks = new ArrayList<>();

        for (StockEntity stockEntity: this.stockRepository.findAll()) {
            stocks.add(StockMapper.entityToDomain(stockEntity));
        }

        return stocks;
    }

    @Override
    public Stock update(Stock stock) throws InvalidStockModelException {
        this.validateStockIsNull(stock);

        StockEntity stockEntity = StockMapper.domainToEntity(stock);

        return StockMapper.entityToDomain(this.stockRepository.save(stockEntity));
    }

    @Override
    public void deleteById(long id) {
        this.stockRepository.deleteById(id);
    }

    private void validateStockIsNull(Stock stock) throws InvalidStockModelException {
        if (stock == null) {
            log.error(INVALID_STOCK_DATA);
            throw new InvalidStockModelException(INVALID_STOCK_DATA);
        }
    }
}
