package com.teamcubation.AccionService.infrastructure.adapter.out.persistance.mapper;

import com.teamcubation.AccionService.domain.model.Stock;
import com.teamcubation.AccionService.infrastructure.adapter.out.persistance.entity.StockEntity;

public class StockMapper {
    public static Stock entityToDomain(StockEntity stockEntity) {
        if (stockEntity == null) {
            throw new StockNotFoundException(STOCK_NOT_FOUND);
        }
        return Stock.builder()
                .id(stockEntity.getId())
                .name(stockEntity.getName())
                .price(stockEntity.getPrice())
                .dividend(stockEntity.getDividend())
                .build();
    }

    public static StockEntity domainToEntity(Stock stock) {
        if (stock == null) {
            throw new StockNotFoundException(STOCK_NOT_FOUND);
        }
        return StockEntity.builder()
                .id(stock.getId())
                .name(stock.getName())
                .price(stock.getPrice())
                .dividend(stock.getDividend())
                .build();
    }
}
