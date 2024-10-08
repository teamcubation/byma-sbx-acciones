package com.teamcubation.AccionService.infrastructure.adapter.out.persistance.repository;

import com.teamcubation.AccionService.infrastructure.adapter.out.persistance.entity.StockEntity;

public class StockMapper {
    public static Stock entityToDomain(StockEntity stockEntity) {
        return Stock.builder()
                .id(stockEntity.getId())
                .name(stockEntity.getName())
                .price(stockEntity.getPrice())
                .build();
    }

    public static StockEntity domainToEntity(Stock stock) {
        return StockEntity.builder()
                .id(stock.getId())
                .name(stock.getName())
                .price(stock.getPrice())
                .build();
    }
}
