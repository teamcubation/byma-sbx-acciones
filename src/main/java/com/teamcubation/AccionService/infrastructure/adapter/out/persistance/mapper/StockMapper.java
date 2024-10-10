package com.teamcubation.AccionService.infrastructure.adapter.out.persistance.mapper;

import com.teamcubation.AccionService.domain.exception.InvalidStockModelException;
import com.teamcubation.AccionService.domain.model.Stock;
import com.teamcubation.AccionService.infrastructure.adapter.out.persistance.entity.StockEntity;
import lombok.extern.slf4j.Slf4j;

import static com.teamcubation.AccionService.application.service.util.validation.ServiceValidation.INVALID_STOCK_DATA;

@Slf4j
public class StockMapper {
    public static Stock entityToDomain(StockEntity stockEntity) throws InvalidStockModelException {
        if (stockEntity == null) {
            log.error(INVALID_STOCK_DATA);
            throw new InvalidStockModelException(INVALID_STOCK_DATA);
        }
        return Stock.builder()
                .id(stockEntity.getId())
                .name(stockEntity.getName())
                .price(stockEntity.getPrice())
                .dividend(stockEntity.getDividend())
                .build();
    }

    public static StockEntity domainToEntity(Stock stock) throws InvalidStockModelException {
        if (stock == null) {
            log.error(INVALID_STOCK_DATA);
            throw new InvalidStockModelException(INVALID_STOCK_DATA);
        }
        return StockEntity.builder()
                .id(stock.getId())
                .name(stock.getName())
                .price(stock.getPrice())
                .dividend(stock.getDividend())
                .build();
    }
}
